package com.sandeep.todoapp.data.repo

import androidx.lifecycle.LiveData
import com.sandeep.todoapp.data.NoteDao
import com.sandeep.todoapp.model.Note
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao
) : NoteRepository {
    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }

    override fun getAllNotesFilteredHeader(header: String): LiveData<List<Note>> {
        return dao.getAllNotesFilteredHeader(header)
    }

    override fun getAllNotesFilteredContent(content: String): LiveData<List<Note>> {
        return dao.getAllNotesFilteredContent(content)
    }

    override fun getAllNotesFilteredDate(timeStamp: Long): LiveData<List<Note>> {
        return dao.getAllNotesFilteredDate(timeStamp)
    }

    override fun getAllNotesOrderedByDate(): LiveData<List<Note>> {
        return dao.getAllNotesOrderedByDate()
    }

    override fun updateCompletion(isCompleted: Boolean, id: Int) {
        dao.updateCompletion(isCompleted, id)
    }

}