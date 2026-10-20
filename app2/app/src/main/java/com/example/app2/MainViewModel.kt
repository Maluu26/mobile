package com.example.app2
import android.app.Application
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.app2.objects.curiosidades

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val sp = application.getSharedPreferences("CHAVE_ACESSO", Context.MODE_PRIVATE)

    private val _nome = MutableLiveData<String>()
    fun nome(): LiveData<String> = _nome
    private val _fraseAtual = MutableLiveData<String>()
    fun fraseAnimais(): LiveData<String> = _fraseAtual

    private val _animalSelecionado = MutableLiveData<String>()
    val animalSelecionado: LiveData<String> = _animalSelecionado

    init {
        val animalSalvo = sp.getString("animal", "Belinha") ?: "Belinha"
        selecionarAnimal(animalSalvo)
        _nome.value = getNomeUsuario()
    }
    fun cadastrarNome(novoNome: String): Boolean{
        if (novoNome != null && novoNome.length>=3){
            sp.edit().putString("name", novoNome).apply()
            return true
        }
        return false
    }
    fun selecionarAnimal(animal: String) {
        _animalSelecionado.value = animal
        sp.edit().putString("animal", animal).apply()
        buscarNovaFrase()
    }

    fun buscarNovaFrase() {
        val novaFrase = when (_animalSelecionado.value) {
            ("Belinha") -> curiosidades.getCuriosidadeBelinha()
            ("Branquinho") -> curiosidades.getCuriosidadesBranquinho()
            ("Juju") -> curiosidades.getCuriosidadesJuju()
            else -> "..."
        }
        _fraseAtual.value = novaFrase
    }
    fun removerFraseAtual(){
        _fraseAtual.value?.let { curiosidades.removeCuriosidades(it) }
        buscarNovaFrase()
    }

    fun getNomeUsuario(): String{
        return sp.getString("name", "") ?: ""

    }

    fun limparUsuario(){
        sp.edit().putString("name", "").apply()
        _nome.value = ""
    }
}
