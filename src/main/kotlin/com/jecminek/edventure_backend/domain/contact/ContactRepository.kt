package com.jecminek.edventure_backend.domain.contact

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactRepository : CrudRepository<Contact, Long> {
    fun findContactsByOwnerId(id: Long): List<Contact>
}