package com.mobile.app6.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Produtos")
class Produto {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name="nome")
    var nome: String = ""

    @ColumnInfo(name="preco")
    var preco: Float = 0f

    @ColumnInfo(name="disponivel")
    var disponivel: Int = 0

}