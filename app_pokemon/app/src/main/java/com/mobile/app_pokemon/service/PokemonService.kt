package com.mobile.app_pokemon.service
import com.mobile.app_pokemon.entity.PokemonEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    @GET()
    fun getAll(): Call<PokemonEntity>

    @GET("{name}/")
    fun getByName(@Path("name")name: String): Call<PokemonEntity>
}

