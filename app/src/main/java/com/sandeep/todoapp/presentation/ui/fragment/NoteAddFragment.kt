package com.sandeep.todoapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sandeep.todoapp.R
import com.sandeep.todoapp.databinding.FragmentNewNoteBinding
import com.sandeep.todoapp.model.Note
import com.sandeep.todoapp.presentation.ui.MainActivity
import com.sandeep.todoapp.presentation.ui.viewmodel.NoteViewModel

class NoteAddFragment(
    private val viewModel: NoteViewModel
) : Fragment() {

    private lateinit var binding: FragmentNewNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var note = Note("", "")
        viewModel.note.value?.apply {
            if (this.header.isNotEmpty() || this.header.isNotBlank()) {
                binding.etHeader.setText(this.header)
                binding.btnSaveUpdate.text = getString(R.string.edit_note)
                note.header = this.header
            }
            if (this.content.isNotEmpty() || this.content.isNotBlank()) {
                binding.etContent.setText(this.content)
                note.content = this.content
                binding.btnSaveUpdate.text = getString(R.string.edit_note)
            } else {
                binding.btnSaveUpdate.text = getString(R.string.save_note)
            }
        }

        binding.btnSaveUpdate.setOnClickListener {
            if (binding.etContent.text.isNullOrBlank()) {
                Toast.makeText(activity, "Content should not be empty!!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (binding.etHeader.text.isNullOrBlank()) {
                Toast.makeText(activity, "Header should not be empty!!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            note = Note(binding.etHeader.text.toString(), binding.etContent.text.toString())
            viewModel.note.value?.id?.let {
                note.id = it
            }
            viewModel.insertNote(note).let { inserted ->
                if (inserted) {
                    Toast.makeText(activity, "Note Inserted Successfully!!", Toast.LENGTH_LONG)
                        .show()
                    viewModel.note.value = Note("", "")
                    (activity as MainActivity).replaceFragment(true)
                }
            }
        }
    }
}