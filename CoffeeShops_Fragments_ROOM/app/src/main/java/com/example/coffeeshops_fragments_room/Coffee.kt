package com.example.coffeeshops_fragments_room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Local")
data class Coffee (
    @PrimaryKey val num: Int,
    @ColumnInfo(name = "nom") val title: String,
    @ColumnInfo(name = "adreca") val subtitle: String?,
    @ColumnInfo(name = "punts") val points: Int?,
    @ColumnInfo(name = "imatge") val image: ByteArray?
)