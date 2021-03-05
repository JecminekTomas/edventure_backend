package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.BaseEntity
import com.jecminek.edventure_backend.domain.lesson.Lesson
import javax.persistence.*

@Entity
class User(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var biography: String? = "",
    var rating: Double? = 2.5,
    var phoneNumber: String? = "",
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "UserRole", joinColumns = [JoinColumn(name = "id")])
//    @Enumerated(EnumType.STRING)
//    var role: List<UserRole> = emptyList(),
    @Enumerated(EnumType.STRING)
    var status: UserStatus = UserStatus.OFFLINE,
    @OneToMany val lessons: List<Lesson> = emptyList()
): BaseEntity()