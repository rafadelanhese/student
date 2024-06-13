package com.c6.student.application.request

import com.c6.student.domain.model.Student
import java.time.LocalDate

data class StudentCreateRequest(
        val name: String,
        val birthDate: LocalDate
){
        fun toEntity() : Student {
                return Student(0L, this.name, this.birthDate)
        }
}
