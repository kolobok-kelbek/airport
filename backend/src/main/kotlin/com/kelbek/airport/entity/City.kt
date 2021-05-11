package com.kelbek.airport.entity

import javax.persistence.*

@Entity
@Table(name = "cities", indexes = [
    Index(name = "city_name_index", columnList = "name"),
    Index(name = "unique_city", columnList = "name,country_id", unique = true)
])
data class City(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    val id: Int,

    @Column(unique = true, nullable = false)
    val name: String,

    @ManyToOne
    @JoinColumn(name="country_id", nullable = false)
    val country: Country,
)
