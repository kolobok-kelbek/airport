package com.kelbek.airport.model.query

import javax.validation.constraints.Positive

data class Passport(
        @Positive
        val number: Int,

        @Positive
        val series: Int,

        @Positive
        val code: Int,

        @Positive
        val date: Long,
)
