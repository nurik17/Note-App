package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.data.Note
import com.example.noteapp.data.NotesDatabase
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.repository.NotesRepo
import com.example.noteapp.viewModel.NotesViewModel
import com.example.noteapp.viewModel.NotesViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    val viewModel : NotesViewModel by lazy {
        val database = NotesDatabase.getDatabaseInstance(this)
        val notesRepo = NotesRepo(database)
        val notesProviderFactory = NotesViewModelProviderFactory(notesRepo)
        ViewModelProvider(this,notesProviderFactory)[NotesViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}