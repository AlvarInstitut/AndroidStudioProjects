package com.example.coffeeshops_fragments_room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comentaris (
    @PrimaryKey @ColumnInfo(name = "id_com") val idCom: Int?,
    @ColumnInfo(name = "num_local") val numLocal: Int?,
    @ColumnInfo(name = "comentari") val comentari: String?
)
