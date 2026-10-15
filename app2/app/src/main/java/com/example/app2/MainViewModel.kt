package com.example.app2
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val sp = application.getSharedPreferences("CHAVE_ACESSO", Context.MODE_PRIVATE)

    private val _fraseAtual = MutableLiveData<String>()
    fun fraseAnimais(): LiveData<String> = _fraseAtual

    private val _animalSelecionado = MutableLiveData<String>()
    val animalSelecionado: LiveData<String> = _animalSelecionado

    init {
        val animalSalvo = sp.getString("animal", "Belinha") ?: "Belinha"
        selecionarAnimal(animalSalvo)
    }

    fun selecionarAnimal(animal: String) {
        _animalSelecionado.value = animal
        sp.edit().putString("animal", animal).apply()
        //buscarNovaFrase()
    }



}
