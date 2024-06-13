package com.c6.student.domain.service.impl

import com.c6.student.application.request.StudentCreateRequest
import com.c6.student.domain.model.Student
import com.c6.student.domain.repository.StudentRepository
import com.c6.student.domain.service.StudentService
import com.c6.student.exception.StudentNotFoundException
import com.c6.student.resource.NoteResource
import com.c6.student.resource.model.note.NoteRequest
import com.c6.student.resource.model.note.NoteResponse
import com.c6.student.resource.model.note.StudentRequest
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class StudentServiceImpl(private val studentRepository: StudentRepository, private val noteResource: NoteResource) : StudentService {

    @Transactional
    override fun create(student: StudentCreateRequest): Student {
        return studentRepository.save(student.toEntity())
    }

    @Transactional
    override fun update(studentId: Long, student: StudentCreateRequest): Student {
        if(studentRepository.existsById(studentId)){
            val newStudent = Student(studentId, student.name, student.birthDate)
            return studentRepository.save(newStudent)
        } else {
            throw StudentNotFoundException("Student not found")
        }
    }

    @Transactional
    override fun listAll(page: Int, pageSize: Int): Page<Student> {
        val pageable = PageRequest.of(page, pageSize)
        return studentRepository.findAll(pageable)
    }

    @Transactional
    override fun findById(studentId: Long): Student {
        return studentRepository.findById(studentId).orElseThrow { throw StudentNotFoundException("Student not found") }
    }

    @Transactional
    override fun deleteById(studentId: Long) {
        this.findById(studentId)
        studentRepository.deleteById(studentId)
    }

    override fun insertNote(studentId: Long) {
        val note = Random.nextInt(0,10)
        val student = findById(studentId)
        val noteRequest = NoteRequest(StudentRequest(student.id, student.name), note)
        noteResource.insertNote(noteRequest)
    }

    override fun getAllNotes(): List<NoteResponse> {
        return noteResource.getAllNotes()
    }
}