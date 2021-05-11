package com.kelbek.airport.controller

import com.kelbek.airport.entity.Passport
import com.kelbek.airport.repository.PassportRepository
import com.kelbek.airport.repository.UserRepository
import com.kelbek.airport.service.PassportProducer
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import java.time.Instant
import javax.validation.Valid

typealias PassportData = com.kelbek.airport.model.data.Passport
typealias PassportQuery = com.kelbek.airport.model.query.Passport

@RestController
@RequestMapping(value = ["/passports"])
class PassportController(
    private val passportProducer: PassportProducer,
) {
    @PostMapping
    fun create(principal: Principal, @Valid @RequestBody passportQuery: PassportQuery): PassportData {

        val passport = passportProducer.produce(principal, passportQuery)

        return PassportData(
            id = passport.id,
            number = passport.number,
            series = passport.series,
            code = passport.code,
            date = passport.date.epochSecond,
        )
    }
}