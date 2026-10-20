package com.example.app2.service

import com.example.app2.entities.curiosidadeCachorro
import retrofit2.Call
import retrofit2.http.GET

interface curiosidadesCachorroService {
    @GET("api/facts")
    fun getDogFact(): Call<curiosidadeCachorro>
}