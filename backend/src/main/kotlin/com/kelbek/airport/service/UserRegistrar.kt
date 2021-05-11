package com.kelbek.airport.service

import com.kelbek.airport.entity.User
import com.kelbek.airport.model.query.SignUp
import com.kelbek.airport.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserRegistrar(
        val userRepository: UserRepository,
        val passwordEncoder: PasswordEncoder
) {
    fun register(signUpCommand: SignUp): User {
        val user = User(
                username = signUpCommand.username,
                password = passwordEncoder.encode(signUpCommand.password),
                email = signUpCommand.email,
                firstName = signUpCommand.firstName,
                lastName = signUpCommand.lastName,
        )

        userRepository.save(user)

        return user
    }
}