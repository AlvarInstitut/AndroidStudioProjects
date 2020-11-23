package com.example.coffeeshops_fragments_room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Coffee::class, Comment::class), version = 1)
abstract class CoffeeShopsDatabase : RoomDatabase() {
    abstract fun coffeeshopsDao(): CoffeeShopsDao
}