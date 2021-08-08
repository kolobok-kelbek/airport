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
    @Column(name = "id", unique = true, nullable = false)
    val id: Int,

    @Column(nullable = false)
    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="country_id", nullable = false)
    val country: Country,
)
