package com.mobile.app_pokemon.Repository

import android.R
import android.content.Context
import android.util.Log
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mobile.app_pokemon.data.entity.Pokemon
import com.mobile.app_pokemon.data.room.AppDatabase

class FavoritosRepository(context: Context) {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val dao = AppDatabase.getDatabase(context).PokemonDao()
    //fun ehFavorito(id: Long): Boolean = dao.getPokemonById(id) != null
    fun adicionar(p: Pokemon) = dao.insert(p)
    fun remover(id: Long) = dao.deleteById(id)
    fun listar(): List<Pokemon> = dao.getFavoritePokemons().orEmpty()

    fun savePokemon(p: Pokemon){
        val userMap = hashMapOf(
            "id" to p.id,
            "nome" to p.nome,
            "url" to p.url,
            "favorito" to true
        )
        val currUserId = auth.currentUser?.email
        if (currUserId != null) {
            db.collection(currUserId).document(p.nome)
                .set(userMap)
                .addOnSuccessListener {
                    print("Salvo com sucesso")
                }
                .addOnFailureListener {
                    print("Problemas ao salvar")
                }
        }
    }
    fun getPokemons(onSucesso: (List<Pokemon>) -> Unit, onErro: (String) -> Unit) {
        val currUserId = auth.currentUser?.email
        if (currUserId == null) {
            onErro("Usuário não está logado")
            return
        }
        db.collection(currUserId).get()
            .addOnSuccessListener { resultado ->
                onSucesso(resultado.toObjects(Pokemon::class.java))
            }
            .addOnFailureListener {
                Log.d("Firebase", "Problema ao ler: ", it)
                onErro("Problema ao carregar os favoritos")
            }
    }


    fun ehFavorito(nome: String, onResultado: (Boolean) -> Unit) {
        val currUserId = auth.currentUser?.email
        if (currUserId == null) {
            onResultado(false)
            return
        }
        db.collection(currUserId).document(nome).get()
            .addOnSuccessListener { doc ->
                onResultado(doc.exists())
            }
            .addOnFailureListener {
                onResultado(false)
            }
    }

    fun deleteFav(nome: String, onSucesso: (String) -> Unit, onErro: (String) -> Unit){
        val currUserId = auth.currentUser?.email
        if (currUserId == null) {
            onErro("Usuário não está logado")
            return
        }
        db.collection(currUserId).document(nome).delete().addOnSuccessListener {
            onSucesso("Pokemon deletado com sucesso")
        }.addOnFailureListener {
                onErro("Falha ao deletar Pokemon")
            }
    }

}