package com.mobile.app6.viewModel

import android.app.Application
import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobile.app6.data.entity.Produto
import com.mobile.app6.data.room.AppDatabase

class AddProductView(application: Application) : AndroidViewModel(application){
    private var savedMsg = MutableLiveData<Int>()

    fun getIsSaved(): LiveData<Int> {
        return savedMsg
    }

    fun saveProduct(p: Produto){
        val db = AppDatabase.getDatabase(getApplication()).ProdutoDao()
        var resp = 0L
        try {
            resp = db.insert(p)
            savedMsg.value = if(resp > 0) 1 else 0
        } catch (e: SQLiteConstraintException){
            savedMsg.value = 2
        }

    }

}