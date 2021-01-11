package com.example.cff

import java.io.Serializable

data class Coffee (val title: String?, val subtitle: String?,
                   val points: Int?, var image: ByteArray? ): Serializable