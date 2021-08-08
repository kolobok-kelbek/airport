package com.kelbek.airport.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "roles", indexes = [
])
data class Role(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        val id: Int,

        @Column(nullable = false, unique = true)
        val name: String,

        @Column(nullable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        val createAt: Date = Date(),
)