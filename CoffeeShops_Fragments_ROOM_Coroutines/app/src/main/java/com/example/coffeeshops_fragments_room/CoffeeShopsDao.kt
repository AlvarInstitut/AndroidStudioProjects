package com.example.coffeeshops_fragments_room

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CoffeeShopsDao {
    @Query("SELECT * FROM LOCAL")
    fun getCoffeesWithComments(): List<CoffeeWithComments>

    @Query("SELECT * FROM LOCAL")
    fun getCoffees(): List<Coffee>

    @Query("SELECT * FROM LOCAL WHERE num = :key")
    fun getCommentsFrom(key: Int): List<CoffeeWithComments>
}