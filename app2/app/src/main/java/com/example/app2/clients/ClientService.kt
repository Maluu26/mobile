package com.example.app2.clients
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientService {
    companion object {
        private lateinit var INSTANCE: Retrofit
        private const val BASE_URL_GATO = "https://catfact.ninja/"

        private const val BASE_URL_DOG = "https://dogapi.dog/"

        private fun getCatClientInstance(): Retrofit {
            val http = OkHttpClient.Builder()
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL_GATO)
                    .client(http.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE
        }

        fun <S> createCatService(className: Class<S>): S {
            return getCatClientInstance().create(className)
        }

        private fun getDogClientInstance(): Retrofit {
            val http = OkHttpClient.Builder()
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL_DOG)
                    .client(http.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE
        }

        fun <S> createDogService(className: Class<S>): S {
            return getDogClientInstance().create(className)
        }
    }
}