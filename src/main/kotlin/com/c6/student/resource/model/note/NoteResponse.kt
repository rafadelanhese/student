package com.c6.student.resource.model.note

data class NoteResponse(
    val id: Long?,
    val note: Int,
    val studentId: Long
)
