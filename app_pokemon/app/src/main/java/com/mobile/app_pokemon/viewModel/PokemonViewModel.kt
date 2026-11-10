package com.mobile.app_pokemon.viewModel

import android.app.Application
import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobile.app_pokemon.data.entity.Pokemon
import com.mobile.app_pokemon.data.room.AppDatabase

class PokemonViewModel(application: Application) : AndroidViewModel(application) {


    private var _isFav = MutableLiveData<Boolean>()
    val isFav: LiveData<Boolean> get() = _isFav

    private var _savedMsg = MutableLiveData<Int>()
    val savedMsg: LiveData<Int> get() = _savedMsg
    val db = AppDatabase.getDatabase(getApplication()).PokemonDao()

    fun adjustFav(id: Long){
        var resp: Pokemon?
        try {
            resp = db.getPokemonById(id)

            if(resp != null){
                _isFav.value = true
                _savedMsg.value = 1
            }else{
                _isFav.value = false
                _savedMsg.value = 0
            }

        } catch (e: SQLiteConstraintException){
            _isFav.value = false
            _savedMsg.value = 2
        }
    }
    fun alternateFilled(id: Long, name: String, url: String){
        if(_isFav.value==true){
            db.deleteById(id)
            _isFav.value = false
        }else
        {   val p = Pokemon(id, name, url, true)
            db.insert(p)
            _isFav.value = true
        }

    }





}