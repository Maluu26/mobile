package com.mobile.app_pokemon.service
import com.mobile.app_pokemon.entity.PokemonDetails
import com.mobile.app_pokemon.entity.PokemonEntity
import com.mobile.app_pokemon.entity.PokemonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon/")
    fun getAll(@Query("limit") limit: Int = 20,
               @Query("offset") offset: Int = 0): Call<PokemonListResponse>

    @GET("pokemon/{name}/")
    fun getByName(@Path("name")name: String): Call<PokemonDetails>
}

