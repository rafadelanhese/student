package com.c6.student.fixture

import com.c6.student.domain.model.Student
import java.time.LocalDate
import java.util.*

class StudentFixture{

    companion object{

        fun studentFixture() : Optional<Student>{
            return Optional.of(Student(10L, "Teste", LocalDate.parse("2024-05-29")))
        }
    }


}
