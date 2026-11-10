package com.mobile.app_pokemon.viewModel

import android.app.Application
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobile.app_pokemon.client.Cliente
import com.mobile.app_pokemon.data.entity.Pokemon
import com.mobile.app_pokemon.data.room.AppDatabase
import com.mobile.app_pokemon.entity.PokemonDetails
import com.mobile.app_pokemon.entity.PokemonEntity
import com.mobile.app_pokemon.entity.PokemonListResponse
import com.mobile.app_pokemon.service.PokemonService
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val _favList = MutableLiveData<List<Pokemon>>()

    val favList: LiveData<List<Pokemon>> get() = _favList
    private val _pokemonList = MutableLiveData<List<PokemonEntity>>()
    val pokemonList: LiveData<List<PokemonEntity>> get() = _pokemonList

    private val _savedMessage = MutableLiveData<Int>()
    val savedMsg: LiveData<Int> get() = _savedMessage
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _pokemonDetails = MutableLiveData<PokemonDetails?>()
    val pokemonDetails: LiveData<PokemonDetails?> get() = _pokemonDetails

    val db = AppDatabase.getDatabase(getApplication()).PokemonDao()
    private val listaAcumulada = mutableListOf<PokemonEntity>()
    private var offset = 0
    private var carregando = false

    fun detalhesAbertos() {
        _pokemonDetails.value = null
    }
    fun requestPokemonList(limit: Int = 30) {
        if (carregando) return
        carregando = true

        val service = Cliente.createService(PokemonService::class.java)
        service.getAll(limit = limit, offset = offset).enqueue(object : Callback<PokemonListResponse> {
            override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
                carregando = false
                val novos = response.body()?.results
                if (response.isSuccessful && novos != null) {
                    listaAcumulada.addAll(novos)
                    offset += novos.size
                    _pokemonList.value = listaAcumulada.toList()
                } else {
                    _errorMessage.value = "Erro HTTP: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                carregando = false
                _errorMessage.value = "Falha de conexão com a PokéAPI"
                Log.e("RetrofitDebug", "Erro: ${t.message}", t)
            }
        })
}
    fun requestPokemon(name: String) {
        val nome = name.lowercase().trim()
        if (nome.isEmpty()) {
            _errorMessage.value = "Digite o nome de um Pokémon"
            return
        }

        val service = Cliente.createService(PokemonService::class.java)
        service.getByName(nome).enqueue(object : Callback<PokemonDetails> {
            override fun onResponse(call: Call<PokemonDetails>, response: Response<PokemonDetails>) {
                val pokemonDetalhado = response.body()
                if (response.isSuccessful && pokemonDetalhado != null) {
                    _pokemonDetails.value = pokemonDetalhado
                } else {
                    _errorMessage.value = "Não existe Pokemon com este nome!"
                }
            }

            override fun onFailure(call: Call<PokemonDetails>, t: Throwable) {
                _errorMessage.value = "Falha de conexão com a PokéAPI"
                Log.e("RetrofitDebug", "Erro: ${t.message}", t)
            }
        })
    }

    fun getFavoritePokemons(){
            val db =  AppDatabase.getDatabase(getApplication()).PokemonDao()
            var resp: List<Pokemon>?
            try {
                resp = db.getFavoritePokemons()
                if(resp == null){
                    _savedMessage.value = 2
                }else{
                    _favList.value = resp
                    _savedMessage.value = 1
                }
            }catch(e: SQLiteConstraintException){
                _savedMessage.value = 3
            }
        }

    fun removeFilled(id: Long){
            db.deleteById(id)
            getFavoritePokemons()
    }
}

