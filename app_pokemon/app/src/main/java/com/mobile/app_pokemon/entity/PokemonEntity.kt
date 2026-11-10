package com.mobile.app_pokemon.entity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class PokemonEntity {


    @SerializedName("name")
    var name: String = ""

    @SerializedName("url")
    var url: String = ""


    fun getId(): Int {
        return url.trimEnd('/').split("/").last().toIntOrNull() ?: 1
    }

    // 2. Monta a URL direta da imagem (Official Artwork)
    fun getImageUrl(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${getId()}.png"
    }
}
data class PokemonDetails(
    @SerializedName("id")
    var id: Long,

    @SerializedName("name")
    var name: String,

    @SerializedName("sprites")
    val sprites: SpriteEntity,

    @SerializedName("stats")
    val stats: List<StatEntity>,

    @SerializedName("types")
    val types: List<PokemonType>
)
data class SpriteEntity(
    @SerializedName("other")
    val other: OtherSpritesEntity
)

data class OtherSpritesEntity(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkEntity
)

data class OfficialArtworkEntity(
    @SerializedName("front_default")
    val frontDefault: String
)

data class Stat(
    @SerializedName("name")
    var name: String,


)

data class StatEntity(
    @SerializedName("base_stat")
    var baseStat: Int,

    @SerializedName("stat")
    var stat: Stat

)

data class PokemonType(
    @SerializedName("type")
    var type: Type,
)

data class Type(
    @SerializedName("name")
    var name: String,
)