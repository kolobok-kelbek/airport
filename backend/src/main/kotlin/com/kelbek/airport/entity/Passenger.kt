package com.kelbek.airport.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "passengers", indexes = [
])
data class Passenger(
        @Id
        @Column(name = "id", length = 16, unique = true, nullable = false, columnDefinition = "UUID")
        val id: UUID = UUID.randomUUID(),

        @OneToOne
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        private val user: User,

        @OneToMany(mappedBy = "passenger", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
        private val tickets: Set<Ticket>,

        @Column(nullable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        val createAt: Date = Date(),

        @Column(nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        val updateAt: Date = Date(),
)