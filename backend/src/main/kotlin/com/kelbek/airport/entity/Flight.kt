package com.kelbek.airport.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "flights", indexes = [
])
data class Flight(
        @Id
        @Column(name = "id", length = 16, unique = true, nullable = false, columnDefinition = "UUID")
        val id: UUID = UUID.randomUUID(),

        @Column(nullable = false, unique = true, updatable = false)
        val number: String,

        @Column(nullable = false)
        val departureTime: Date,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="route_id", columnDefinition = "UUID", nullable = false)
        val route: Route,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="aircrew_id", columnDefinition = "UUID", nullable = false)
        val aircrew: Aircrew,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="airplane_id", columnDefinition = "UUID", nullable = false)
        val airplane: Airplane,

        @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
        val tickets: Set<Ticket>,

        @Column(nullable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        val createAt: Date = Date(),

        @Column(nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        val updateAt: Date = Date(),
)