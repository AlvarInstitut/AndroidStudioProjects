package com.example.roomempleattels

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Empleat::class,Telefons::class), version = 1)
abstract class EmpleatDatabase : RoomDatabase() {
    abstract fun empleatDao(): EmpleatDao
}
