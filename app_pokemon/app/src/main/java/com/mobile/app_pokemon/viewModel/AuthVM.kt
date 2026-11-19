package com.mobile.app_pokemon.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobile.app_pokemon.Repository.AuthRepository

class AuthVM(application: Application) : AndroidViewModel(application) {

    private val _msg = MutableLiveData<Result<Unit>>()

    val msg : LiveData<Result<Unit>> get() = _msg

    private var authRepository = AuthRepository()
    fun subscribeUser(name: String,apelido: String, email: String, password: String, checkPassword: String){
        if (email.isBlank() ||apelido.isBlank() ||  password.isBlank() || name.isBlank() || checkPassword.isBlank()) {
            _msg.value = Result.failure(Exception("Preencha todos os campos"))
            return
        }
        if (password != checkPassword) {
            _msg.value = Result.failure(Exception("Senhas incompativéis"))
            return
        }
        authRepository.registerUser(name, apelido,email, password) { result ->
            _msg.value = result
        }
    }
    fun loginUser(email: String, password: String){
        if (email.isBlank() || password.isBlank()) {
            _msg.value = Result.failure(Exception("Preencha todos os campos"))
            return
        }
        authRepository.loginUser(email, password) { result ->
            _msg.value = result
        }
    }
}