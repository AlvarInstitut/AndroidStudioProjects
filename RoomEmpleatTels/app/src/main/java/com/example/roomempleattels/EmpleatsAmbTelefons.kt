package com.example.roomempleattels

import androidx.room.Embedded
import androidx.room.Relation

data class EmpleatsAmbTelefons (
    @Embedded val empleat: Empleat,
    @Relation(
        parentColumn = "num",
        entityColumn = "num_emp"
    )
    val telefons: List<Telefons>
)