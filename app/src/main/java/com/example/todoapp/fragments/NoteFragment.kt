package com.example.todoapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.MainActivity
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentNoteBinding
import com.example.todoapp.databinding.NotesItemBinding
import com.example.todoapp.mvvm.NotesViewModel
import com.example.todoapp.room.Note

class NoteFragment : Fragment() {
    private lateinit var binding: FragmentNoteBinding
    private lateinit var viewModel : NotesViewModel
    private val args : NoteFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.note.let {
            binding.apply {
                ed1.setText(it?.title)
                ed2.setText(it?.text)
            }
            binding.deleteImg.visibility = View.VISIBLE
        }
         binding.apply {
             fabSaveNote.setOnClickListener {
                 val id  = args.note?.noteId ?: 0
                 val noteTitle = ed1.text.toString()
                 val noteText = ed2.text.toString()

                 Note(id,noteTitle,noteText).also { note->
                     if(noteTitle.isEmpty() && noteText.isEmpty()){
                         Toast.makeText(context, "All fields must be filled", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                     }
                     viewModel.upsertNote(note)
                     findNavController().navigateUp()
                 }
             }
         }
        binding.apply {
            deleteImg.setOnClickListener {
                val noteId = args.note!!.noteId
                val noteTitle = ed1.text.toString()
                val noteText = ed2.text.toString()
                Note(noteId,noteTitle,noteText).also {
                    viewModel.delete(it)
                    findNavController().navigateUp()
                }
            }
        }
    }
}