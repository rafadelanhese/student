package com.c6.student.domain.model

import com.c6.student.application.request.StudentCreateRequest
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity(name = "student")
class Student(

    @Id
    @GeneratedValue
    val id: Long,

    @Column(name = "name")
    val name: String,

    @Column(name = "birth_date")
    val birthDate: LocalDate

){
    fun birthDateFormatted() : String{
        return this.birthDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }
}