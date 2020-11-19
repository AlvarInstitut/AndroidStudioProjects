package com.example.coffeeshops_fragments_room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Local::class,Comentaris::class), version = 1)
abstract class CoffeeShopsDatabase : RoomDatabase() {
    abstract fun localDao(): LocalDao
}