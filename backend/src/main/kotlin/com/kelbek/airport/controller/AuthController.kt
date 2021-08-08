package com.kelbek.airport.controller

import com.kelbek.airport.model.query.SignUp
import com.kelbek.airport.repository.UserRepository
import com.kelbek.airport.service.UserRegistrar
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

typealias UserData = com.kelbek.airport.model.data.User

@RestController
@RequestMapping(value = ["/auth"])
class AuthController(
    private val userRepository: UserRepository,
    private val userRegistrar: UserRegistrar
) {
    @PostMapping("/signup")
    fun singUp(@Valid @RequestBody signUpCommand: SignUp): UserData {
        if (userRepository.findByUsername(signUpCommand.username) != null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this username already exist.")
        }

        val user = userRegistrar.register(signUpCommand)

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
