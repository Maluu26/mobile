package com.mobile.app3.MainViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobile.app3.client.ClienteRetrofit
import com.mobile.app3.entities.Post
import com.mobile.app3.services.PostService
import okhttp3.Callback
import retrofit2.Call
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var postAtual = MutableLiveData<String>()

    fun getPostAtual(): LiveData<String>{ return postAtual}

    fun requestPost(id: String){
        val  apiPostService = ClienteRetrofit.createService(PostService::class.java)
        val post: Call<Post> = apiPostService.getSinglePost(id)
        post.enqueue(object: retrofit2.Callback<Post> {
            override fun onResponse(
                call: Call<Post>,
                response: Response<Post>
            ) {
                if(response.isSuccessful){
                postAtual.value = response.body()?.body}
             else {
                // Erros HTTP (ex: 404 Not Found, 500 Internal Server Error)
                val erroHttp = "Erro HTTP: ${response.code()}"
                Log.e("RetrofitDebug", "Erro na resposta: ${response.errorBody()?.string()}")
                postAtual.value = erroHttp
            }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                postAtual.value = "Falha na requisição da API"
                Log.e("RetrofitDebug", "Falha crítica: ${t.message}", t)
            }
        })
    }
}