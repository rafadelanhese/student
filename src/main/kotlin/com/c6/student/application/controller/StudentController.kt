package com.c6.student.application.controller

import com.c6.student.application.request.StudentCreateRequest
import com.c6.student.application.response.StudentCreateResponse
import com.c6.student.application.swagger.StudentControllerSwagger
import com.c6.student.domain.service.StudentService
import com.c6.student.resource.model.note.NoteResponse
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/student")
class StudentController(private val studentService: StudentService) : StudentControllerSwagger {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun getAllStudents(
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(name = "page-size", defaultValue = "20") pageSize: Int
    ): ResponseEntity<Page<StudentCreateResponse>> {
        val allStudentsResponse = studentService.listAll(page, pageSize).map { student ->  StudentCreateResponse(student.id, student.name, student.birthDateFormatted())}
        return ResponseEntity.ok(allStudentsResponse)
    }

    @GetMapping("/{studentId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun getStudentById(@PathVariable studentId: Long): ResponseEntity<StudentCreateResponse> {
        val student = studentService.findById(studentId)
        val studentResponse = StudentCreateResponse(student.id, student.name, student.birthDateFormatted())
        return ResponseEntity.ok(studentResponse)
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    override fun createStudent(@RequestBody student: StudentCreateRequest): ResponseEntity<StudentCreateResponse> {
        val student = studentService.create(student)
        val studentResponse = StudentCreateResponse(student.id, student.name, student.birthDateFormatted())
        return ResponseEntity.status(HttpStatus.CREATED).body(studentResponse)
    }

    @PutMapping(
        "/{studentId}",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    override fun updateStudent(
        @PathVariable studentId: Long,
        @RequestBody student: StudentCreateRequest
    ): ResponseEntity<StudentCreateResponse> {
        val studentUpdated = studentService.update(studentId, student)
        val studentResponse =
            StudentCreateResponse(studentUpdated.id, studentUpdated.name, studentUpdated.birthDateFormatted())
        return ResponseEntity.ok(studentResponse)
    }

    @DeleteMapping("/{studentId}")
    override fun deleteStudentById(@PathVariable studentId: Long): ResponseEntity<Void> {
        studentService.deleteById(studentId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @PostMapping(
        "/{studentId}/insert-note",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    override fun insertNote(@PathVariable studentId: Long): ResponseEntity<Void> {
        val create = studentService.insertNote(studentId)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/all-notes", produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun getAllNotes(): ResponseEntity<List<NoteResponse>> {
        val allNotes = studentService.getAllNotes()
        return ResponseEntity.ok(allNotes)
    }
}