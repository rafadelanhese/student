package com.c6.student.service.impl

import com.c6.student.application.request.StudentCreateRequest
import com.c6.student.exception.StudentNotFoundException
import com.c6.student.fixture.StudentFixture
import com.c6.student.domain.model.Student
import com.c6.student.domain.repository.StudentRepository
import com.c6.student.domain.service.impl.StudentServiceImpl
import com.c6.student.resource.NoteResource
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mockito.*
import java.time.LocalDate
import java.util.*

/*
* Essa anotação retira a necessidade do MockkInstantiate.init(this)
* */
@ExtendWith(MockKExtension::class)
class StudentServiceImplTest {

    @InjectMocks
    lateinit var studentService: StudentServiceImpl

    @MockK
    lateinit var studentRepository: StudentRepository

    @MockK
    lateinit var noteResource: NoteResource

    val studentId = 10L

    @BeforeEach
    fun setUp(){
        this.studentService = StudentServiceImpl(studentRepository, noteResource)
    }

    @Test
    fun create() {
        every { studentRepository.save<Student>(any()) } returns StudentFixture.studentFixture().get()

        val studentCreateRequest = StudentCreateRequest("Teste", LocalDate.parse("2024-05-29"))
        val studentCreateResponse = studentService.create(studentCreateRequest)

        assertAll("All tests should be a true",
            { assertEquals(StudentFixture.studentFixture().get().id, studentCreateResponse.id) },
            { assertEquals(StudentFixture.studentFixture().get().name, studentCreateResponse.name) },
            { assertEquals(StudentFixture.studentFixture().get().birthDate, studentCreateResponse.birthDate) })
    }

    @Test
    fun listAll() {
    }

    @Test
    fun findById() {
        every { studentRepository.findById(any()) } returns StudentFixture.studentFixture()

        val studentCreateResponse = studentService.findById(studentId)

        assertAll("All tests should be a true",
            { assertEquals(StudentFixture.studentFixture().get().id, studentCreateResponse.id) },
            { assertEquals(StudentFixture.studentFixture().get().name, studentCreateResponse.name) },
            { assertEquals(StudentFixture.studentFixture().get().birthDate, studentCreateResponse.birthDate) })
    }

    @Test
    fun deleteById() {
        every { studentRepository.findById(studentId) } returns StudentFixture.studentFixture()

        verify(exactly = 1) { studentRepository.deleteById(studentId) }
    }

    @Test
    fun shouldReturnStudentNotFoundException(){
        every { studentRepository.findById(any()) } returns Optional.empty()

        assertThrows(StudentNotFoundException::class.java){
            studentService.findById(studentId)
        }
    }
}