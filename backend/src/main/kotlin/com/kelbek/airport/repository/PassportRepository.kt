package com.kelbek.airport.repository

import com.kelbek.airport.entity.Passport
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PassportRepository : JpaRepository<Passport, Long> {
}
