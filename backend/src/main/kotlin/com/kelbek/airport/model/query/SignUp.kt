package com.kelbek.airport.model.query

import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class SignUp(
        @Min(4)
        @Max(32)
        val username: String,

        @Min(8)
        @Max(128)
        val password: String,

        @Email
        val email: String,

        @Min(1)
        @Max(255)
        val firstName: String,

        @Min(1)
        @Max(255)
        val lastName: String,
)
