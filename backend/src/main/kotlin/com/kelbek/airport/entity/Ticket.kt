package com.kelbek.airport.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tikets", indexes = [
])
data class Ticket(
        @Id
        @Column(name = "id", length = 16, unique = true, nullable = false)
        val id: UUID = UUID.randomUUID(),

        @Column(length = 4, unique = true, nullable = false)
        val seat: String,

        @Column(nullable = false)
        val hasBag: Boolean,

        @ManyToOne
        @JoinColumn(name="passenger_id", nullable = false)
        val passenger: Passenger,

        @ManyToOne
        @JoinColumn(name="flight_id", nullable = false)
        val flight: Flight,

        @Column(nullable = false)
        val createAt: Date = Date(),
)
