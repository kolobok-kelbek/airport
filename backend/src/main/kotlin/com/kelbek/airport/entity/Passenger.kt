package com.kelbek.airport.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "passengers", indexes = [
])
data class Passenger(
        @Id
        @Column(name = "id", length = 16, unique = true, nullable = false)
        val id: UUID = UUID.randomUUID(),

        @OneToOne
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        private val user: User,

        @OneToMany(mappedBy = "passenger")
        private val tickets: Set<Ticket>,

        @Column(nullable = false)
        val createAt: Date = Date(),

        @Column(nullable = false)
        val updateAt: Date = Date(),
)