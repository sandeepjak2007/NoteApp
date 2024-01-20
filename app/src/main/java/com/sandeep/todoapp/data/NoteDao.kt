package com.sandeep.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sandeep.todoapp.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note): Long

    @Delete
    fun deleteNote(note: Note): Int

    @Query("SELECT * FROM notes WHERE header LIKE :header")
    fun getAllNotesFilteredHeader(header: String): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE content LIKE :content")
    fun getAllNotesFilteredContent(content: String): LiveData<List<Note>>

    @Query("SELECT * from notes WHERE timeStamp LIKE :timeStamp")
    fun getAllNotesFilteredDate(timeStamp: Long): LiveData<List<Note>>

    @Query("SELECT * from notes ORDER BY timeStamp DESC")
    fun getAllNotesOrderedByDate(): LiveData<List<Note>>

    @Query("UPDATE notes SET isCompleted=:isCompleted WHERE id=:id")
    fun updateCompletion(isCompleted: Boolean, id: Int)

}