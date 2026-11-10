package com.mobile.app_pokemon.data.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mobile.app_pokemon.data.entity.Pokemon

@Dao
interface PokemonDao {
    @Insert
    fun insert (p: Pokemon): Long

    @Update
    fun update(p: Pokemon): Int

    @Delete
    fun delete(p: Pokemon)

    @Query("SELECT * FROM Pokemons Where favorito = 1")
    fun getFavoritePokemons(): List<Pokemon>?

    @Query("SELECT * FROM Pokemons")
    fun getAllPokemons():List<Pokemon>

    @Query("SELECT * FROM Pokemons Where id = :id")
    fun getPokemonById(id: Long): Pokemon?

    @Query("DELETE FROM Pokemons WHERE id = :id")
    fun deleteById(id: Long)


}





