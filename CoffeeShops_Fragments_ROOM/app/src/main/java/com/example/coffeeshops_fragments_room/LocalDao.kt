package com.example.coffeeshops_fragments_room

import androidx.room.Dao
import androidx.room.Query

@Dao
interface LocalDao {
    @Query("SELECT * FROM LOCAL")
    fun getLocals(): List<LocalsAmbComentaris>
}