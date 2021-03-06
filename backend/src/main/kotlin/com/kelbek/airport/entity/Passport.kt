package com.kelbek.airport.entity

import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "passports", indexes = [
])
data class Passport(
        @Id
        @Column(name = "id", length = 16, unique = true, nullable = false, columnDefinition = "UUID")
        val id: UUID = UUID.randomUUID(),

        @Column(nullable = false)
        val number: Int,

        @Column(nullable = false)
        val series: Int,

        @Column(nullable = false)
        val code: Int,

        @Column(nullable = false)
        val date: Instant,

        @Column(nullable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        val createAt: Date = Date(),

        @Column(nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        val updateAt: Date = Date(),
)
