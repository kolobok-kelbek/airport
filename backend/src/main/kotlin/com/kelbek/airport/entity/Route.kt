package com.kelbek.airport.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "routers", indexes = [
])
data class Route(
        @Id
        @Column(name = "id", length = 16, unique = true, nullable = false, columnDefinition = "UUID")
        val id: UUID = UUID.randomUUID(),

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="from_airport_id", columnDefinition = "UUID", nullable = false)
        val from: Airport,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="to_airport_id", columnDefinition = "UUID", nullable = false)
        val to: Airport,

        @Column(nullable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        val createAt: Date = Date(),
)