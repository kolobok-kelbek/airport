package com.kelbek.airport.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "routers", indexes = [
])
data class Route(
        @Id
        @Column(name = "id", length = 16, unique = true, nullable = false)
        val id: UUID = UUID.randomUUID(),

        @ManyToOne
        @JoinColumn(name="from_airport_id", nullable = false)
        val from: Airport,

        @ManyToOne
        @JoinColumn(name="to_airport_id", nullable = false)
        val to: Airport,
)