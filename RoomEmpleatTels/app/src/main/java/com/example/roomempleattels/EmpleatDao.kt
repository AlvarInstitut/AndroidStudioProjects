package com.example.roomempleattels

import androidx.room.*

@Dao
interface EmpleatDao {

/*    @Query("SELECT * from EMPLEAT")
    fun getEmpleats(): List<Empleat>
*/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(e: Empleat)

    @Query("DELETE FROM Empleat")
    suspend fun deleteAll()

    @Transaction
    @Query("SELECT * FROM EMPLEAT")
    fun getEmpeatsAmbTelefons(): List<EmpleatsAmbTelefons>

}