package com.sandeep.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sandeep.todoapp.model.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object {
        const val DB_NAME = "note_db"
    }
}