package com.mobile.app_pokemon.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pokemons")
class Pokemon(
    @PrimaryKey
    var id: Long = 0L,

    @ColumnInfo(name = "nome")
    var nome: String = "",

    @ColumnInfo(name = "url")
    var url: String = "",

    @ColumnInfo(name = "favorito")
    var favorito: Boolean = false
)
