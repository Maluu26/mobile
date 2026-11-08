package com.mobile.app_pokemon.client
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Cliente {
    companion object {
        private lateinit var INSTANCE: Retrofit
        private const val BASE_URL = "https://pokeapi.co/api/v2/pokemon"

        private val client: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .build()
        }

        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun <S> createService(className: Class<S>): S {
            return retrofit.create(className)
        }

    }

}




