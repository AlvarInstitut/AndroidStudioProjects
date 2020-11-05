package com.example.roomempleat

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Empleat::class), version = 1)
abstract class EmpleatDatabase : RoomDatabase() {
    abstract fun empleatDao(): EmpleatDao
}
