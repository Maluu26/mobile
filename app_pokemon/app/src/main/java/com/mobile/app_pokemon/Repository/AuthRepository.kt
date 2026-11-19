package com.mobile.app_pokemon.Repository

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore

class AuthRepository {
    private val auth = FirebaseAuth.getInstance()

    private val db = FirebaseFirestore.getInstance()

    fun registerUser(
        nome: String,
        apelido: String,
        email: String,
        senha: String,
        result: (Result<Unit>) -> Unit
    ) {
        val suc = saveUser(nome, apelido)
        auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener { it ->
            if (it.isSuccessful) {
                result(Result.success(Unit))
                saveUser(nome, apelido)
            }

        }
            .addOnFailureListener {
                val msg = when (it) {
                    is FirebaseAuthInvalidCredentialsException -> "Digite um email válido"
                    is FirebaseAuthWeakPasswordException -> "Digite uma senha com no mínimo 6 caracteres"
                    is FirebaseAuthUserCollisionException -> "Este e-mail já foi cadastrado"
                    is FirebaseNetworkException -> "Falha ao conectar com a internet"
                    else -> "Houve algum problema no cadastro"
                }
                result(Result.failure(Exception(msg)))
            }

    }

    fun loginUser(email: String, senha: String, result: (Result<Unit>) -> Unit) {
        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener { it ->
            if (it.isSuccessful) {
                result(Result.success(Unit))
            }
        }
            .addOnFailureListener {
                val msg = when (it) {
                    is FirebaseAuthInvalidCredentialsException -> "O usuário existe mas a senha está incorreta"
                    is FirebaseAuthInvalidUserException -> "Este usuário não existe"
                    is FirebaseNetworkException -> "Falha ao conectar com a internet"
                    else -> "Houve algum problema no login, tente novamente"
                }
                result(Result.failure(Exception(msg)))
            }

    }

    fun saveUser(name: String, nick: String) {
        val userMap = hashMapOf(
            "nome" to name,
            "apelido" to nick
        )
        val currUserId = auth.currentUser?.email
        if (currUserId != null) {
            db.collection("users").document(currUserId)
                .set(userMap)
                .addOnSuccessListener {
                   print("Salvo com sucesso")
                }
                .addOnFailureListener {
                    print("Problemas ao salvar")
                }
        }

    }
}