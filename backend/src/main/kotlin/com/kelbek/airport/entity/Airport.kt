package com.kelbek.airport.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "airports", indexes = [
])
data class Airport(
        @Id
        @Column(name = "id", length = 16, unique = true, nullable = false)
        val id: UUID = UUID.randomUUID(),

        @Column(unique = true, nullable = false)
        val name: String,

        @Column
        val latitude: Float,

        @Column
        val longitude: Float,

        @Column(length = 4, unique = true, nullable = false)
        val iataCode: Float,

        @Column(length = 4, unique = true, nullable = false)
        val icaoCode: Float,

        @ManyToOne
        @JoinColumn(name="city_id", nullable = false)
        val city: City,
)