package com.example.coffeeshops_fragments_room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Local (
    @PrimaryKey val num: Int?,
    val nom: String?,
    val adreca: String?,
    val punts: Int?,
    val imatge: ByteArray?
)