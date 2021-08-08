package com.kelbek.airport.entity

import org.locationtech.jts.geom.Point
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "airports", indexes = [
])
data class Airport(
        @Id
        @Column(name = "id", length = 16, unique = true, nullable = false, columnDefinition = "UUID")
        val id: UUID = UUID.randomUUID(),


        @Column(nullable = false)
        val name: String,

        @Column(length = 4, unique = true, nullable = false)
        val iataCode: String,

        @Column(columnDefinition = "geography(POINT)")
        val coordinate: Point,

        @Column(length = 4)
        val icaoCode: String?,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "city_id", nullable = false)
        val city: City,
) : Serializable
