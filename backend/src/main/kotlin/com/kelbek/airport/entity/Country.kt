package com.kelbek.airport.entity

import javax.persistence.*

@Entity
@Table(name = "countries", indexes = [
    Index(name = "country_name_index", columnList = "name"),
])
data class Country(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    val id: Int,

    @Column(unique = true, nullable = false)
    val name: String,

    @Column(length = 4, unique = true, nullable = false)
    val code: String,

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    private val cities: Set<City>,
)
