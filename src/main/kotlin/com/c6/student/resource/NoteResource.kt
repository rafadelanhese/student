package com.c6.student.resource

import com.c6.student.resource.model.note.NoteRequest
import com.c6.student.resource.model.note.NoteResponse

interface NoteResource {
    fun insertNote(noteRequest: NoteRequest)
    fun getAllNotes(): List<NoteResponse>
}