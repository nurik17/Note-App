package com.example.todoapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.ItemDecoration
import com.example.todoapp.MainActivity
import com.example.todoapp.NotesAdapter
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentNotesListBinding
import com.example.todoapp.mvvm.NotesViewModel
import kotlinx.coroutines.launch


class NotesListFragment : Fragment() {
    private lateinit var binding: FragmentNotesListBinding
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var viewModel : NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesListBinding.inflate(inflater,container,false)
        return binding.root
    }


    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allNotes.collect{notesList->
                    notesAdapter.differ.submitList(notesList)
            }
        }
    }

        binding.fabAddNote.setOnClickListener {
            findNavController().navigate(
                R.id.action_notesListFragment_to_noteFragment
            )
        }

        notesAdapter.onClick = {note->
            val bundle = Bundle().apply {
                putParcelable("note",note)
            }
            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment,bundle)
        }
    }


    private fun setUpRecyclerView(){
        notesAdapter = NotesAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = notesAdapter
        binding.recyclerView.addItemDecoration(ItemDecoration(32))
    }
}