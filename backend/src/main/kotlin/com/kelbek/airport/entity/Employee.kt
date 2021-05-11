package com.kelbek.airport.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "employees", indexes = [
])
data class Employee(
        @Id
        @Column(name = "id", length = 16, unique = true, nullable = false)
        val id: UUID = UUID.randomUUID(),

        @Column(unique = true, nullable = false)
        val phone: String,

        @OneToOne
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        private val user: User,

        @ManyToOne
        @JoinColumn(name="aircrew_id")
        val aircrew: Aircrew,

        @Column(nullable = false)
        val createAt: Date = Date(),

        @Column(nullable = false)
        val updateAt: Date = Date(),
)