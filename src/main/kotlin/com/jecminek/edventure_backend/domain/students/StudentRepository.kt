package com.jecminek.edventure_backend.domain.students

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository: PagingAndSortingRepository<Student, Long> {

    fun findByLastName(lastName: String): List<Student>

    // TODO: ADD UPDATE
    // TODO: findByPriceBetween/lower/greater
    // TODO: findByRatingGreater
}