package com.example.coffeeshops_fragments_room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="COMENTARIS")
data class Comment (
    @PrimaryKey @ColumnInfo(name = "id_com") val idCom: Int?,
    @ColumnInfo(name = "num_local") val numCoffee: Int?,
    @ColumnInfo(name = "comentari")val comm: String?
)