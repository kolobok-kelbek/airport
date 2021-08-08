package com.kelbek.airport.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "seats", indexes = [
])
data class Seat(
        @Id
        @Column(name = "id", length = 16, unique = true, nullable = false, columnDefinition = "UUID")
        val id: UUID = UUID.randomUUID(),

        @Column
        val row: Int,

        @Column
        val col: String,

        @Column
        val type: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="airplane_id", columnDefinition = "UUID", nullable = false)
        val airplane: Airplane,
)
