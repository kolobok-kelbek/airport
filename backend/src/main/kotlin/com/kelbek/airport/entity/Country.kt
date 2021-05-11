package com.kelbek.airport.entity

import javax.persistence.*

@Entity
@Table(name = "countries", indexes = [
    Index(name = "country_name_index", columnList = "name"),
])
data class Country(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    val id: Int,

    @Column(unique = true, nullable = false)
    val name: String,

    @Column(length = 4, unique = true, nullable = false)
    val code: String,

    @OneToMany(mappedBy = "country")
    private val cities: Set<City>,
)
