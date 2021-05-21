package com.kelbek.airport.model.data

import java.util.*

data class Airport(
        val id: UUID,
        val name: String,
        val iataCode: String,
        val coordinate: Coordinate,
        val icaoCode: String?,
        val city: City,
)
