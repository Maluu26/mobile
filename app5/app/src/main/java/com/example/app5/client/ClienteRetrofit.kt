package com.example.app5.client

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClienteRetrofit {
    companion object {
        private lateinit var INSTANCE: Retrofit
        private const val BASE_URL = "https://viacep.com.br/ws/"

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
        /*private fun getClientInstance(): Retrofit {
            val http = OkHttpClient.Builder()
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(http.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE
        }*/

        fun <S> createService(className: Class<S>): S {
            return retrofit.create(className)
        }
    }
}
