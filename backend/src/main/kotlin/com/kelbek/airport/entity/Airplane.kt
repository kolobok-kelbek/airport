package com.kelbek.airport.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "airplanes", indexes = [
])
data class Airplane(
        @Id
        @Column(name = "id", length = 16, unique = true, nullable = false, columnDefinition = "UUID")
        val id: UUID = UUID.randomUUID(),

        @Column
        val type: String,

        @Column
        val model: String,

        @OneToMany(mappedBy = "airplane", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
        private val seats: Set<Seat>,
)
