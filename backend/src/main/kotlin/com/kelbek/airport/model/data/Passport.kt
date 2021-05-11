package com.kelbek.airport.model.data

import java.util.*

data class Passport(
        val id: UUID,

        val number: Int,

        val series: Int,

        val code: Int,

        val date: Long,
)
