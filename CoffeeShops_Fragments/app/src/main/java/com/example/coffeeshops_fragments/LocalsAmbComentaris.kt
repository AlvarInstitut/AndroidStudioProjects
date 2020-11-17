package com.example.coffeeshops_fragments

import androidx.room.Embedded
import androidx.room.Relation

data class LocalsAmbComentaris (
    @Embedded val local: Local,
    @Relation(
        parentColumn = "num",
        entityColumn = "num_local"
    )
    val coms: List<Comentaris>
)