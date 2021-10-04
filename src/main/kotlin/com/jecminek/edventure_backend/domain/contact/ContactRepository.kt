package com.jecminek.edventure_backend.domain.contact

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactRepository : PagingAndSortingRepository<Contact, Long> {
    fun findContactsByContactOwnerId(id: Long): List<Contact>
}