package com.example.coffeeshopskotlin

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Local::class), version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun localDao(): LocalDao
}