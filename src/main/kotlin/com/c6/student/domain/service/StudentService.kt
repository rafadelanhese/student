package com.c6.student.domain.service

import com.c6.student.application.request.StudentCreateRequest
import com.c6.student.application.response.StudentCreateResponse
import com.c6.student.domain.model.Student
import com.c6.student.resource.model.note.NoteResponse
import org.springframework.data.domain.Page

interface StudentService {
    fun create(student : StudentCreateRequest) : Student
    fun listAll(page: Int, pageSize:Int) : Page<Student>
    fun findById(studentId: Long): Student
    fun deleteById(studentId: Long)
    fun insertNote(studentId: Long)
    fun getAllNotes(): List<NoteResponse>
    fun update(studentId: Long, student: StudentCreateRequest): Student
}