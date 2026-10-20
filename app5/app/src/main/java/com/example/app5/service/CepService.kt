package com.example.app5.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CepService {
    @GET("ws/{cep}/json/")
    fun getCepData(@Path("cep")cep: String): Call<CepService>
}
