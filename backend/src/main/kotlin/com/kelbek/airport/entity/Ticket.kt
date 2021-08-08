package com.kelbek.airport.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tikets", indexes = [
])
data class Ticket(
        @Id
        @Column(name = "id", length = 16, unique = true, nullable = false, columnDefinition = "UUID")
        val id: UUID = UUID.randomUUID(),

        @Column(nullable = false)
        val hasBag: Boolean,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="passenger_id", columnDefinition = "UUID", nullable = false)
        val passenger: Passenger,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="seat_id", columnDefinition = "UUID", nullable = false)
        val seat: Seat,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="flight_id", nullable = false)
        val flight: Flight,

        @Column(nullable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        val createAt: Date = Date(),
)
