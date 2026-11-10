package com.mobile.app_pokemon.Repository

import android.content.Context
import com.mobile.app_pokemon.data.entity.Pokemon
import com.mobile.app_pokemon.data.room.AppDatabase

class FavoritosRepository(context: Context) {

    private val dao = AppDatabase.getDatabase(context).PokemonDao()
    fun ehFavorito(id: Long): Boolean = dao.getPokemonById(id) != null
    fun adicionar(p: Pokemon) = dao.insert(p)
    fun remover(id: Long) = dao.deleteById(id)
    fun listar(): List<Pokemon> = dao.getFavoritePokemons().orEmpty()
}