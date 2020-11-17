package com.example.roomempleattels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TELEFONS")
data class Telefons (
    @PrimaryKey @ColumnInfo(name = "id_tel") val idTel: Int?,
    @ColumnInfo(name = "num_emp") val numEmp: Int?,
    @ColumnInfo(name = "tel") val tel: Int?
)