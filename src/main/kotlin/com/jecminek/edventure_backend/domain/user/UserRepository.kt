package com.jecminek.edventure_backend.domain.user

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : PagingAndSortingRepository<User, Long> {

    // TODO: 17.08.2021 GET RID OF QUERY

//    @Query("SELECT u FROM User u JOIN u.roles r JOIN u.subject s WHERE s.university = :university AND s.faculty = :faculty AND s.title = :title AND u.roles = :role")
//    fun findTeachersBySubjectTitle(university: University, faculty: Faculty, title: String, page: Pageable, role: UserRole): Page<User>
}