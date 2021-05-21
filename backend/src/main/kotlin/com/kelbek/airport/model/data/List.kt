package com.kelbek.airport.model.data

data class List<T>(
        val data: Iterable<T>,
        val extra: Extra,
)

