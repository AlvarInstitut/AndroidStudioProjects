package com.example.coffeeshopsroom

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coffeeshopsroom.Local
import com.example.coffeeshopsroom.LocalDao

@Database(entities = arrayOf(Local::class), version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun localDao(): LocalDao
}