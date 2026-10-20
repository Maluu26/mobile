package com.example.app2.service

import com.example.app2.entities.curiosidadeGatos
import retrofit2.Call
import retrofit2.http.GET

interface curiosidadesGatosService {
    @GET("fact")
    fun getCatFact():Call<curiosidadeGatos>
}