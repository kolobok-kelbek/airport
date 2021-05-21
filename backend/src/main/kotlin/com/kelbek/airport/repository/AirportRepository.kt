package com.kelbek.airport.repository

import com.kelbek.airport.entity.Airport
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AirportRepository : JpaRepository<Airport, UUID>
