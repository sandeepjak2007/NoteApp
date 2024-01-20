package com.sandeep.todoapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sandeep.todoapp.R
import com.sandeep.todoapp.databinding.ListItemBinding
import com.sandeep.todoapp.model.Note
import com.sandeep.todoapp.presentation.ui.MainActivity
import com.sandeep.todoapp.presentation.ui.viewmodel.NoteViewModel

class NoteAdapter(
    private val viewModel: NoteViewModel,
    private val mainActivity: MainActivity
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemBinding.bind(view)
        fun bind(note: Note) {
            with(binding) {
                noteHeader.text = note.header
                noteContent.text = note.content
                if (note.isCompleted) {
                    ivRead.setImageDrawable(
                        ContextCompat.getDrawable(
                            itemView.context,
                            R.drawable.fish_eye
                        )
                    )
                    cl.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,R.color.black
                        )
                    )
                } else {
                    ivRead.setImageDrawable(
                        ContextCompat.getDrawable(
                            itemView.context,
                            R.drawable.eye_open
                        )
                    )
                }

                ivDelete.setOnClickListener {
                    viewModel.deleteNote(note).let { deleted ->
                        if (deleted) {
                            Toast.makeText(
                                itemView.context,
                                "Deleted Successfully",
                                Toast.LENGTH_LONG
                            ).show()

                        }
                    }
                }
                ivEdit.setOnClickListener {
                    viewModel.note.value = note
                    mainActivity.replaceFragment(false)
                }
                ivRead.setOnClickListener {
                    viewModel.note.value = note
                    note.id?.let {
                        viewModel.updateCompletion(!note.isCompleted, it).let { updated ->
                            if(updated){
                                Toast.makeText(
                                    itemView.context,
                                    "${if(note.isCompleted) "Read" else "UnRead"} Successfully",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }

                }
            }
        }
    }

    private val differCall = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private val differ = AsyncListDiffer(this, differCall)

    fun submitList(list: List<Note>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }
}