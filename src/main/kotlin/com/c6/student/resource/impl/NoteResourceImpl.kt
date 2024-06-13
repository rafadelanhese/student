package com.c6.student.resource.impl

import com.c6.student.exception.ConnectionRefuseException
import com.c6.student.resource.NoteResource
import com.c6.student.resource.client.NoteClient
import com.c6.student.resource.model.note.NoteRequest
import com.c6.student.resource.model.note.NoteResponse
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.retry.annotation.Retry
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class NoteResourceImpl(private val noteClient : NoteClient) : NoteResource {

    @Retry(name = "insertNoteRetry", fallbackMethod = "insertNoteFallback")
    @CircuitBreaker(name = "insertNoteCircuitBreaker", fallbackMethod = "insertNoteFallback")
    override fun insertNote(noteRequest: NoteRequest) {
        noteClient.insertNote(noteRequest)
    }

    @Retry(name = "getAllNotesRetry", fallbackMethod = "getAllNotesFallback")
    @CircuitBreaker(name = "getAllNotesCircuitBreaker", fallbackMethod = "getAllNotesFallback")
    override fun getAllNotes(): List<NoteResponse> {
        return noteClient.getAllNotes()
    }

    fun insertNoteFallback(exception: Exception) {
        println(message = "Connection Refuse: " + LocalDateTime.now())
        throw ConnectionRefuseException(exception.message)
    }

    fun getAllNotesFallback(exception: Exception) {
        println(message = "[Get all notes fallback]: " + LocalDateTime.now())
        throw ConnectionRefuseException(exception.message)
    }
}