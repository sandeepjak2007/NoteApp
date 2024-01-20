package com.sandeep.todoapp.data.repo

import androidx.lifecycle.LiveData
import com.sandeep.todoapp.model.Note

interface NoteRepository {

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

    fun getAllNotesFilteredHeader(header: String): LiveData<List<Note>>

    fun getAllNotesFilteredContent(content:String): LiveData<List<Note>>

    fun getAllNotesFilteredDate(timeStamp:Long): LiveData<List<Note>>

    fun getAllNotesOrderedByDate(): LiveData<List<Note>>

    fun updateCompletion(isCompleted: Boolean, id: Int)
}