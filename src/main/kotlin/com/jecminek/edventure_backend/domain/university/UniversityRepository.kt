package com.jecminek.edventure_backend.domain.university

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UniversityRepository: CrudRepository<University, Long>