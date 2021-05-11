package com.kelbek.airport.model.data

import com.kelbek.airport.entity.Passport
import java.util.*

data class User (
        val id: UUID,

        val username: String,

        val firstName: String,

        val secondName: String? = null,

        val lastName: String,

        val email: String,

        val passport: Passport? = null,

        val createAt: Long,

        val updateAt: Long,
)
