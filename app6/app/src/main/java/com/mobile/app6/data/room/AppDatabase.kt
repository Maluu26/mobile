package com.mobile.app6.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobile.app6.data.dao.ProdutoDao
import com.mobile.app6.data.entity.Produto

@Database(entities = [Produto::class], version = 1)
abstract class AppDatabase(): RoomDatabase() {

    abstract fun ProdutoDao(): ProdutoDao
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