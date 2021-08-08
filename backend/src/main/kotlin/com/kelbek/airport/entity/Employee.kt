package com.kelbek.airport.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "employees", indexes = [
])
data class Employee(
        @Id
        @Column(name = "id", length = 16, unique = true, nullable = false, columnDefinition = "UUID")
        val id: UUID = UUID.randomUUID(),

        @Column(unique = true, nullable = false)
        val phone: String,

        @OneToOne
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        private val user: User,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="aircrew_id", columnDefinition = "UUID")
        val aircrew: Aircrew,

        @Column(nullable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        val createAt: Date = Date(),

        @Column(nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        val updateAt: Date = Date(),
)