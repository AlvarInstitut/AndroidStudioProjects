package com.example.coffeeshops_fragments

import androidx.room.Embedded
import androidx.room.Relation
import java.io.Serializable

data class LocalsAmbComentaris (
    @Embedded val local: Local,
    @Relation(
        parentColumn = "num",
        entityColumn = "num_local"
    )
    val coms: List<Comentaris>
): Serializable