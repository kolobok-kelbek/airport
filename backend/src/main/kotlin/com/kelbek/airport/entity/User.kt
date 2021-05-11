package com.kelbek.airport.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users", indexes = [
        Index(name = "username_index", columnList = "username"),
])
data class User(
        @Id
        @Column(name = "id", length = 16, unique = true, nullable = false, columnDefinition = "UUID")
        val id: UUID = UUID.randomUUID(),

        @Column(unique=true, nullable = false, updatable = false, length = 32)
        val username: String,

        @Column(nullable = false, length = 128)
        val password: String,

        @Column(nullable = false)
        val firstName: String,

        @Column
        val secondName: String? = null,

        @Column(nullable = false)
        val lastName: String,

        @Column(unique = true, nullable = false)
        val email: String,

        @OneToOne
        @JoinColumn(name = "passport_id", referencedColumnName = "id")
        var passport: Passport? = null,

        @OneToMany(mappedBy = "user")
        val roles: Set<Role>? = null,

        @Column(nullable = false, updatable = false)
        val createAt: Date = Date(),

        @Column(nullable = false)
        val updateAt: Date = Date(),
)
