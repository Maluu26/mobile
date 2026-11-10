package com.mobile.app_pokemon.Repository

import android.content.Context
import android.util.Log
import com.mobile.app_pokemon.client.Cliente
import com.mobile.app_pokemon.data.room.AppDatabase
import com.mobile.app_pokemon.entity.PokemonDetails
import com.mobile.app_pokemon.entity.PokemonEntity
import com.mobile.app_pokemon.entity.PokemonListResponse
import com.mobile.app_pokemon.service.PokemonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepository(context: Context){
    
    val service = Cliente.createService(PokemonService::class.java)

    fun requestPokemonList(limit: Int = 30, offset: Int = 30, onSucesso: (List<PokemonEntity>) -> Unit,
                           onErro: (String) -> Unit){

        service.getAll(limit = limit, offset = offset).enqueue(object : Callback<PokemonListResponse> {
            override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
                val novos = response.body()?.results
                if (response.isSuccessful && novos != null) {
                   onSucesso(novos)
                } else {
                  onErro("Erro HTTP: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                onErro("Falha de conexão com a PokéAPI")
                Log.e("RetrofitDebug", "Erro: ${t.message}", t)
            }
        })
    }
    fun requestPokemon(nome: String, onSucesso: (PokemonDetails) -> Unit,
                       onErro: (String) -> Unit ) {

        service.getByName(nome).enqueue(object : Callback<PokemonDetails> {
            override fun onResponse(call: Call<PokemonDetails>, response: Response<PokemonDetails>) {
                val pokemonDetalhado = response.body()
                if (response.isSuccessful && pokemonDetalhado != null) {
                   onSucesso(pokemonDetalhado)
                } else {
                   onErro( "Não existe Pokemon com este nome!")
                }
            }
            override fun onFailure(call: Call<PokemonDetails>, t: Throwable) {
                onErro( "Falha de conexão com a PokéAPI")
                Log.e("RetrofitDebug", "Erro: ${t.message}", t)
            }
        })
    }

}