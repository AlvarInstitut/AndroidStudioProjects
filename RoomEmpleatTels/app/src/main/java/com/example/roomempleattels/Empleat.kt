package com.example.roomempleattels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Empleat(
    @PrimaryKey @ColumnInfo(name = "num") val num: Int?,
    @ColumnInfo(name = "nom") val nom: String?,
    @ColumnInfo(name = "depart") val departament: Int?,
    @ColumnInfo(name = "edat") val edat: Int?,
    @ColumnInfo(name = "sou") val sou: Double?
)

