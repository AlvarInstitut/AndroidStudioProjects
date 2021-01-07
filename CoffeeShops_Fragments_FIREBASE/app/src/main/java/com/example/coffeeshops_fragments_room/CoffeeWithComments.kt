package com.example.coffeeshops_fragments_room

import androidx.room.Embedded
import androidx.room.Relation
import java.io.Serializable

data class CoffeeWithComments (
    @Embedded val coffee: Coffee,
    @Relation(
        parentColumn = "num",
        entityColumn = "num_local"
    )
    val coms: List<Comment>
): Serializable