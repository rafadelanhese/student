package com.c6.student.domain.repository

import com.c6.student.domain.model.Student
import jakarta.transaction.Transactional
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : CrudRepository<Student, Long>, PagingAndSortingRepository<Student, Long> {
}