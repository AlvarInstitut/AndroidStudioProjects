package com.example.coffeeshops_fragments

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coffeeshops_fragments.Local
import com.example.coffeeshops_fragments.LocalDao

@Database(entities = arrayOf(Local::class,Comentaris::class), version = 1)
abstract class CoffeeShopsDatabase : RoomDatabase() {
    abstract fun localDao(): LocalDao
}