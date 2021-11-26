package com.example.empleatroom

import androidx.room.*

@Dao
interface EmpleatsDAO {
    @Insert
    fun insert(e: Empleat)

    @Delete
    fun delete(e: Empleat)

    @Update
    fun update(e: Empleat)

    @Query("SELECT * FROM EMPLEAT")
    fun getEmpleats() : List<Empleat>
}