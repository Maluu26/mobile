package com.mobile.app_pokemon.listener

import com.mobile.app_pokemon.data.entity.Pokemon

interface OnFavoritesListener {
    fun onClickFav(p: Pokemon)

    fun onClick(p: Pokemon)
}