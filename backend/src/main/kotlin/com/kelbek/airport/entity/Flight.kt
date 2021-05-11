package com.kelbek.airport.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "flights", indexes = [
])
data class Flight(
        @Id
        @Column(name = "id", length = 16, unique = true, nullable = false)
        val id: UUID = UUID.randomUUID(),

        @Column(nullable = false, unique = true, updatable = false)
        val number: String,

        @Column(nullable = false)
        val departureTime: Date,

        @ManyToOne
        @JoinColumn(name="route_id", nullable = false)
        val route: Route,

        @ManyToOne
        @JoinColumn(name="aircrew_id", nullable = false)
        val aircrew: Aircrew,

        @ManyToOne
        @JoinColumn(name="airplane_id", nullable = false)
        val airplane: Airplane,

        @OneToMany(mappedBy = "flight")
        val tickets: Set<Ticket>,

        @Column(nullable = false)
        val createAt: Date = Date(),

        @Column(nullable = false)
        val updateAt: Date = Date(),
)