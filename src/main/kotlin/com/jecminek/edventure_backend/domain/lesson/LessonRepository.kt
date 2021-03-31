package com.jecminek.edventure_backend.domain.lesson

import org.hibernate.hql.internal.antlr.HqlSqlTokenTypes.FROM
import org.hibernate.hql.internal.antlr.HqlSqlTokenTypes.SELECT
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface LessonRepository : PagingAndSortingRepository<Lesson, Long> {

    //@Query("SELECT * FROM Lesson JOIN User where :teachersId == ")
    fun findLessonByTeachersId(teachersId: Long): MutableList<Lesson>?

    fun findLessonByStudentsId(studentsId: Long): MutableList<Lesson>?
}