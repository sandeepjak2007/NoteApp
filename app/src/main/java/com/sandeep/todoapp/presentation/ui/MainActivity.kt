package com.sandeep.todoapp.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sandeep.todoapp.R
import com.sandeep.todoapp.databinding.ActivityMainBinding
import com.sandeep.todoapp.presentation.ui.fragment.NoteAddFragment
import com.sandeep.todoapp.presentation.ui.fragment.NotesFragment
import com.sandeep.todoapp.presentation.ui.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(true)
    }

    fun replaceFragment(isList: Boolean) {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment,
            if (isList) NotesFragment(viewModel) else NoteAddFragment(viewModel)
        ).addToBackStack("note")
            .commit()
    }
}