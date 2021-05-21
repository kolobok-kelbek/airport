package com.kelbek.airport.controller

import com.kelbek.airport.model.data.*
import com.kelbek.airport.model.data.List
import com.kelbek.airport.repository.AirportRepository
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*

@RestController
@RequestMapping(value = ["/airports"])
class AirportController(
        private val airportRepository: AirportRepository,
) {
    @GetMapping
    fun find(principal: Principal, @RequestParam(defaultValue = "0") page: Int, @RequestParam(defaultValue = "10") size: Int): List<Airport> {
        val airportList = airportRepository.findAll(PageRequest.of(page, size))

        val modelList = mutableListOf<Airport>()

        for (airportEntity in airportList) {
            val city = airportEntity.city
            val country = city.country
            val airportModel = Airport(
                    id = airportEntity.id,
                    name = airportEntity.name,
                    iataCode = airportEntity.iataCode,
                    coordinate = Coordinate(airportEntity.coordinate.x, airportEntity.coordinate.y),
                    icaoCode = airportEntity.icaoCode,
                    city = City(
                            id = city.id,
                            name = city.name,
                            country = Country(
                                    id = country.id,
                                    name = country.name,
                                    code = country.code
                            )
                    ),
            )

            modelList.add(airportModel)
        }

        val count = airportRepository.count()

        return List(modelList, ExtraPage(page, size, (count / size).toInt()))
    }

    @GetMapping("/{id}")
    fun find(principal: Principal, @PathVariable(value = "id") id: UUID): Airport? {
        val airportOptional = airportRepository.findById(id)

        if (airportOptional.isEmpty) {
            return null
        }

        val airport = airportOptional.get()
        val city = airport.city
        val country = city.country

        return Airport(
                id = airport.id,
                name = airport.name,
                iataCode = airport.iataCode,
                coordinate = Coordinate(airport.coordinate.x, airport.coordinate.y),
                icaoCode = airport.icaoCode,
                city = City(
                        id = city.id,
                        name = city.name,
                        country = Country(
                                id = country.id,
                                name = country.name,
                                code = country.code
                        )
                ),
        )
    }
}




















