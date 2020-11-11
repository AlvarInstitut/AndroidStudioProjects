package com.example.coffeeshopskotlin

import androidx.room.Dao
import androidx.room.Query

@Dao
interface LocalDao {
    @Query("SELECT * FROM LOCAL")
    fun getLocals(): List<Local>
}