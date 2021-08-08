package com.kelbek.airport.controller

import com.kelbek.airport.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.security.Principal

@CrossOrigin
@RestController
@RequestMapping(value = ["/current"])
class CurrentController(
        private val userRepository: UserRepository,
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/user")
    fun getCurrentUser(principal: Principal?): UserData {
        if (principal == null) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        }

        val user = userRepository.findByUsername(principal.name)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")

        return UserData(
                id = user.id,
                username = user.username,
                email = user.email,
                firstName = user.firstName,
                lastName = user.lastName,
                secondName = user.secondName,
                passport = user.passport,
                createAt = user.createAt.toInstant().epochSecond,
                updateAt = user.updateAt.toInstant().epochSecond,
        )
    }
}