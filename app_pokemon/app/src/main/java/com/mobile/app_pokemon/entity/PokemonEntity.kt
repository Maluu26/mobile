package com.mobile.app_pokemon.entity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class PokemonEntity {

    @SerializedName("name")
    var name: String = ""

    @SerializedName("url")
    var url: String = ""

}