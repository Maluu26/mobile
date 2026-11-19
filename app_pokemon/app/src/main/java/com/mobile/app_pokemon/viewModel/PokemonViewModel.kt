package com.mobile.app_pokemon.viewModel

import android.app.Application
import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import com.mobile.app_pokemon.Repository.FavoritosRepository
import com.mobile.app_pokemon.data.entity.Pokemon
import com.mobile.app_pokemon.data.room.AppDatabase

class PokemonViewModel(application: Application) : AndroidViewModel(application) {


    private var _isFav = MutableLiveData<Boolean>()
    val isFav: LiveData<Boolean> get() = _isFav

    private var _savedMsg = MutableLiveData<Int>()
    val savedMsg: LiveData<Int> get() = _savedMsg

    private var _mesg = MutableLiveData<String>()
    val mesg: LiveData<String> get() = _mesg
    val favApi = FavoritosRepository(getApplication())
    fun adjustFav(nome: String){
        var resp: Boolean
        try {
            favApi.ehFavorito(nome, onResultado = {exists -> _isFav.value = exists})

            if( isFav.value == true){

                _savedMsg.value = 1
            }else{
                _savedMsg.value = 0
            }

        } catch (e: SQLiteConstraintException){
            _isFav.value = false
            _savedMsg.value = 2
        }
    }
    fun alternateFilled(id: Long, name: String, url: String){
        if(_isFav.value==true){
            favApi.deleteFav(name, onSucesso = {msg -> _mesg.value = msg}, onErro = {msg -> _mesg.value = msg})
            _isFav.value = false


        }else
        {   val p = Pokemon(id, name, url, true)
            favApi.savePokemon(p)
            _isFav.value = true
        }

    }





}