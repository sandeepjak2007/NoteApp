package com.sandeep.todoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    var header: String,
    var content: String,
    val isCompleted: Boolean = false,
    val timeStamp: Long = System.currentTimeMillis()
) {
    @PrimaryKey
    var id: Int? = null
}

class InvalidNoteException(message: String) : Exception(message)
