package com.mobile.app_pokemon.listener

import com.mobile.app_pokemon.data.entity.Pokemon
import com.mobile.app_pokemon.entity.PokemonEntity

interface OnPokemonListener {
    fun onClick(p: PokemonEntity)
    fun onClickFav(p: Pokemon)
}