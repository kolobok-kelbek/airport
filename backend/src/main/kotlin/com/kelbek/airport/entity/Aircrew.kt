package com.kelbek.airport.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "aircrews", indexes = [
])
data class Aircrew(
        @Id
        @Column(name = "id", length = 16, unique = true, nullable = false, columnDefinition = "UUID")
        val id: UUID = UUID.randomUUID(),

        @OneToMany(mappedBy = "aircrew", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
        private val employees: Set<Employee>,
)
