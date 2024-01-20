package com.sandeep.todoapp.presentation.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandeep.todoapp.data.repo.NoteRepository
import com.sandeep.todoapp.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    val notes = repository.getAllNotesOrderedByDate()

    val note = MutableLiveData(Note("", ""))

    private val initialValues = arrayListOf(
        Note(
            "Persistent Vigilance",
            "Maintain an ongoing security posture with continuous penetration testing to identify and address vulnerabilities promptly.",
            isCompleted = true
        ),
        Note(
            "Dynamic Threat Simulation",
            "Mimic real-world attack scenarios to assess your defenses and fortify your security measures."
        ),
        Note(
            "Rapid Response",
            "Receive immediate insights into emerging threats, enabling swift response and remediation",
            isCompleted = true
        ),
        Note(
            "Scalable Solutions",
            "Scale penetration testing efforts to match your evolving infrastructure and business landscape"
        ),

        )

    init {
        viewModelScope.launch(Dispatchers.IO) {
            initialValues.forEach {
                insertNote(it)
            }
        }
    }

    fun insertNote(note: Note): Boolean {
        return try {
            viewModelScope.launch(Dispatchers.IO) {
                val list: List<Note>? = repository.getAllNotesFilteredHeader(note.header).value
                if (list.isNullOrEmpty()) {
                    repository.insertNote(note)
                }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun deleteNote(note: Note): Boolean {
        return try {
            viewModelScope.launch(Dispatchers.IO) {
                repository.deleteNote(note)
            }
            true

        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun updateCompletion(isCompleted: Boolean, id: Int): Boolean {
        return try {
            viewModelScope.launch(Dispatchers.IO) {
                repository.updateCompletion(isCompleted, id)
            }
            true

        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}