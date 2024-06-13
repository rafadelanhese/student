package com.c6.student.handler

import com.c6.student.exception.ConnectionRefuseException
import com.c6.student.exception.StudentNotFoundException
import com.c6.student.handler.model.ErrorDetail
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDate
import java.time.LocalDateTime

@ControllerAdvice
class ControllerAdviceRequestError : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [(StudentNotFoundException::class)])
    fun handlerStudentNotExists(exception: StudentNotFoundException, request: WebRequest): ResponseEntity<ErrorDetail>{
        val error = ErrorDetail("Student not found handler", exception.message!!, LocalDateTime.now().toString())
        return ResponseEntity(error, HttpStatus.NOT_FOUND)

    }

    @ExceptionHandler(value = [(ConnectionRefuseException::class)])
    fun handlerConnectionRefuseException(exception: ConnectionRefuseException, request: WebRequest): ResponseEntity<ErrorDetail>{
        val error = ErrorDetail("Connection Refuse", exception.message!!, LocalDateTime.now().toString())
        return ResponseEntity(error, HttpStatus.SERVICE_UNAVAILABLE)

    }
}