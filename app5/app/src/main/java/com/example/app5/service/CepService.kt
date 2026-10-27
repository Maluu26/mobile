package com.example.app5.service

import com.example.app5.entity.CepEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CepService {
    @GET("{cep}/json/")
    fun getCepData(@Path("cep")cep: String): Call<CepEntity>
}
