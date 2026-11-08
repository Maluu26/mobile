package com.mobile.app_pokemon.data.room

import com.mobile.app_pokemon.data.dao.PokemonDao
import com.mobile.app_pokemon.data.entity.Pokemon
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Pokemon::class], version = 1)
abstract class AppDatabase(): RoomDatabase() {

    abstract fun PokemonDao(): PokemonDao
    companion object {
        private lateinit var INSTANCE: AppDatabase
        fun getDatabase(context: Context): AppDatabase {

            if(!::INSTANCE.isInitialized) {

                synchronized(AppDatabase::class) {

                    INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "mydatabase.db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

    }
}