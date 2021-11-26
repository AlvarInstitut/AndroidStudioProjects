package com.example.empleatroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Empleat (
    @PrimaryKey val num: Int?,
    val nom: String?,
    @ColumnInfo(name = "depart") val departament: Int?,
    val edat: Int?,
    val sou: Float?
)