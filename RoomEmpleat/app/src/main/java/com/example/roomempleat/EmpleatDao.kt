package com.example.roomempleat

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmpleatDao {

    @Query("SELECT * from EMPLEAT")
    fun getEmpleats(): List<Empleat>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(e: Empleat)

    @Query("DELETE FROM Empleat")
    suspend fun deleteAll()

}