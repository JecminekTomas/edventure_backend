package com.jecminek.edventure_backend.domain.user

import com.jecminek.edventure_backend.enums.Faculty
import com.jecminek.edventure_backend.enums.University
import com.jecminek.edventure_backend.enums.UserRole
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findUserByRoles(role: UserRole, page: Pageable): Page<User>

    // TODO: 17.08.2021 GET RID OF QUERY

//    @Query("SELECT u FROM User u JOIN u.roles r JOIN u.subject s WHERE s.university = :university AND s.faculty = :faculty AND s.title = :title AND u.roles = :role")
//    fun findTeachersBySubjectTitle(university: University, faculty: Faculty, title: String, page: Pageable, role: UserRole): Page<User>
}