package com.jecminek.edventure_backend.domain.subject

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface SubjectRepository : PagingAndSortingRepository<Subject, Long>