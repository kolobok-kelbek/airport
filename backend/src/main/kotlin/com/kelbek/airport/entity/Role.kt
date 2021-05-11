package com.kelbek.airport.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "roles", indexes = [
])
data class Role(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(unique = true, nullable = false)
        val id: Int,

        @Column(nullable = false, unique = true)
        val  name: String,

        @ManyToOne
        @JoinColumn(name="user_id", nullable = false)
        val user: User,

        @Column(nullable = false)
        val createAt: Date = Date(),
)