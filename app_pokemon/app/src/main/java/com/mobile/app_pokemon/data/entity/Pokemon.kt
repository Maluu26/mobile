package com.mobile.app_pokemon.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pokemons")
class Pokemon {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0


    @ColumnInfo(name="nome")
    var nome: String = ""

    @ColumnInfo(name="url")
    var url:  String = ""



}

