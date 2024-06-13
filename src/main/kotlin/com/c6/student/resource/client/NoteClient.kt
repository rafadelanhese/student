package com.c6.student.resource.client

import com.c6.student.resource.model.note.NoteRequest
import com.c6.student.resource.model.note.NoteResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(value = "notes", url = "http://localhost:8081/note")
interface NoteClient {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE],  produces = [MediaType.APPLICATION_JSON_VALUE])
    fun insertNote(@RequestBody noteRequest: NoteRequest)

    @GetMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllNotes(): List<NoteResponse>
}