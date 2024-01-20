package com.sandeep.todoapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sandeep.todoapp.databinding.FragmentListBinding
import com.sandeep.todoapp.presentation.ui.MainActivity
import com.sandeep.todoapp.presentation.ui.adapter.NoteAdapter
import com.sandeep.todoapp.presentation.ui.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment(
    private val viewModel: NoteViewModel
) : Fragment() {

    private lateinit var binding: FragmentListBinding

    private lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.notes.observe(viewLifecycleOwner) { notes ->
            notes?.let {
                noteAdapter.submitList(it)
                noteAdapter.notifyItemChanged(0, it.size - 1)
            }

        }
        binding.btnAddToDo.setOnClickListener {
            (activity as MainActivity).replaceFragment(false)
        }
    }

    private fun setupRecyclerView() = binding.recyclerView.apply {
        noteAdapter = NoteAdapter(viewModel, activity as MainActivity)
        adapter = noteAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }
}