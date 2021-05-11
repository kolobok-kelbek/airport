package com.kelbek.airport.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "airplanes", indexes = [
])
data class Airplane(
        @Id
        @Column(name = "id", length = 16, unique = true, nullable = false)
        val id: UUID = UUID.randomUUID(),

        @Column
        val type: String,

        @Column
        val model: String,

        @Column
        val storeys: Int,

        @Column
        val rows: Int,

        @Column
        val columns: Int
) {
        fun getSize(): Int {
                return storeys * rows * columns
        }
}
