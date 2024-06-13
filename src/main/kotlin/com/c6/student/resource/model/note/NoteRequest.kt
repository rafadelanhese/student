package com.c6.student.resource.model.note

data class NoteRequest(
    val student: StudentRequest,
    val note: Int
){

}
