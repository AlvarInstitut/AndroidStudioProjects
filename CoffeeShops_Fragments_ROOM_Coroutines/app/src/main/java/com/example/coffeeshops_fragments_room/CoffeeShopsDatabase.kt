package com.example.coffeeshops_fragments_room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Coffee::class, Comment::class), version = 1)
abstract class CoffeeShopsDatabase : RoomDatabase() {
    abstract val coffeeShopsDao: CoffeeShopsDao

    companion object {
        @Volatile
        private var INSTANCE: CoffeeShopsDatabase? = null

        fun getInstance(context: Context): CoffeeShopsDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            CoffeeShopsDatabase::class.java,
                            "CoffeeShops.sqlite"
                    )       .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}