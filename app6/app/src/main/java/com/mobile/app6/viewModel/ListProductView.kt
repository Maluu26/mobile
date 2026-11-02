package com.mobile.app6.viewModel

import android.app.Application
import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobile.app6.data.entity.Produto
import com.mobile.app6.data.room.AppDatabase

class ListProductView(application: Application): AndroidViewModel(application) {

    private var savedMsg = MutableLiveData<Int>()

    fun getIsSaved(): LiveData<Int> {
        return savedMsg
    }
    private  var produto = MutableLiveData<List<Produto>>()

    fun getProduto(): LiveData<List<Produto>>{
        return produto
    }


    fun getTodosProdutos(){
        val db =  AppDatabase.getDatabase(getApplication()).ProdutoDao()
        var resp: List<Produto>?
        try {
            resp = db.getTodosProdutos()
            if(resp == null){
                savedMsg.value = 2
            }else{
                produto.value = resp
                savedMsg.value = 1
            }
        }catch(e: SQLiteConstraintException){
            savedMsg.value = 3
        }
    }


}