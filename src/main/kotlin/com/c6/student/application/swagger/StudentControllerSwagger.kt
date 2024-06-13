package com.c6.student.application.swagger

import com.c6.student.application.request.StudentCreateRequest
import com.c6.student.application.response.StudentCreateResponse
import com.c6.student.resource.model.note.NoteResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

interface StudentControllerSwagger {
    @Operation(summary = "Get all students with peageable", description = "Returns 200 if sucessful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation")
        ]
    )

    fun getAllStudents(
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(name = "page-size", defaultValue = "20") pageSize: Int
    ): ResponseEntity<Page<StudentCreateResponse>>

    fun getStudentById(@PathVariable studentId: Long): ResponseEntity<StudentCreateResponse>

    fun createStudent(@RequestBody student: StudentCreateRequest): ResponseEntity<StudentCreateResponse>

    fun updateStudent(
        @PathVariable studentId: Long,
        @RequestBody student: StudentCreateRequest
    ): ResponseEntity<StudentCreateResponse>

    fun deleteStudentById(@PathVariable studentId: Long): ResponseEntity<Void>

    fun insertNote(@PathVariable studentId: Long): ResponseEntity<Void>

    fun getAllNotes(): ResponseEntity<List<NoteResponse>>
}