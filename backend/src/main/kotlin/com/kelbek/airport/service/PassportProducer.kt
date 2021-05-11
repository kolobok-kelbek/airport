package com.kelbek.airport.service

import com.kelbek.airport.controller.PassportQuery
import com.kelbek.airport.entity.Passport
import com.kelbek.airport.entity.User
import com.kelbek.airport.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.security.Principal
import java.time.Instant
import javax.persistence.EntityManager

@Service
class PassportProducer(
    private val userRepository: UserRepository,
    private val entityManager: EntityManager
) {
    @Transactional
    fun produce(principal: Principal, passportQuery: PassportQuery): Passport {
        val user: User = userRepository.findByUsername(principal.name)!!

        val passport = Passport(
            number = passportQuery.number,
            series = passportQuery.series,
            code = passportQuery.code,
            date = Instant.ofEpochSecond(passportQuery.date),
        )

        user.passport = passport

        entityManager.persist(passport)
        entityManager.persist(user)
        entityManager.flush()

        return passport
    }
}