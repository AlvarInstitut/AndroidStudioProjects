package com.example.coffeeshops_fragments_room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Local (
    @PrimaryKey @ColumnInfo(name = "num") val num: Int?,
    @ColumnInfo(name = "nom") val nom: String?,
    @ColumnInfo(name = "adreca") val adreca: String?,
    @ColumnInfo(name = "punts") val punts: Int?,
    @ColumnInfo(name = "imatge") val imatge: ByteArray?
)