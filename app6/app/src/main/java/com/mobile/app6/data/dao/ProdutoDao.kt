package com.mobile.app6.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mobile.app6.data.entity.Produto

@Dao
interface ProdutoDao {

    @Insert
    fun insert (p: Produto): Long

    @Update
    fun update(p: Produto): Int

    @Delete
    fun delete(p: Produto)

    @Query("SELECT * FROM Produtos Where nome = :nome")
    fun getProdutoByName(nome: String): Produto?

    @Query("SELECT * FROM Produtos Where disponivel= :av")
    fun getProdutosDisponiveis(av: Int): List<Produto>

    @Query("SELECT * FROM Produtos")
    fun getTodosProdutos():List<Produto>?
}