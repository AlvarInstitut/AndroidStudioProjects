package com.example.empleatroom

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Empleat::class), version = 1)
abstract class EmpleatsDatabase:RoomDatabase() {
    abstract fun empleatsDao(): EmpleatsDAO
}