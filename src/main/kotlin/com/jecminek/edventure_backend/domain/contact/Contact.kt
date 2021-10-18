package com.jecminek.edventure_backend.domain.contact

import com.jecminek.edventure_backend.base.BaseEntity
import com.jecminek.edventure_backend.domain.user.User
import com.jecminek.edventure_backend.enums.ContactType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "CONTACT")
class Contact(
    var value: String = "",
    var contactType: ContactType = ContactType.NONE,

    @ManyToOne(fetch = FetchType.EAGER)
    var owner: User = User()
) : BaseEntity()

fun Contact.convertToResponse() = ContactResponse(
    id = id,
    contactType = contactType,
    value = value
)