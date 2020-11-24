package com.example.coffeeshops_fragments_room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName="Comentaris",
        foreignKeys = arrayOf(
          ForeignKey(entity = Coffee::class,
            parentColumns = arrayOf("num"),
            childColumns = arrayOf("num_local"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE)))
data class Comment (
    @PrimaryKey @ColumnInfo(name = "id_com") val idCom: Int,
    @ColumnInfo(name = "num_local") val numCoffee: Int,
    @ColumnInfo(name = "comentari")val comm: String
)