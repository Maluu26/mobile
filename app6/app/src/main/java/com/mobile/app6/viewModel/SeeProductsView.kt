package com.mobile.app6.viewModel

import android.app.Application
import android.database.sqlite.SQLiteConstraintException
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobile.app6.data.entity.Produto
import com.mobile.app6.data.room.AppDatabase

class SeeProductsView(application: Application) : AndroidViewModel(application){
    private var savedMsg = MutableLiveData<Int>()

    fun getIsSaved(): LiveData<Int> {
        return savedMsg
    }
    private  var produto = MutableLiveData<Produto>()

    fun getProduto(): LiveData<Produto>{
        return produto
    }


    fun getProdutos(nome: String){
       val db =  AppDatabase.getDatabase(getApplication()).ProdutoDao()
        var resp: Produto?
        try {
            resp = db.getProdutoByName(nome)
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