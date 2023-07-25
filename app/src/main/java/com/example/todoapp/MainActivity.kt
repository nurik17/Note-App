package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.mvvm.NotesViewModel
import com.example.todoapp.mvvm.NotesViewModelFactory
import com.example.todoapp.mvvm.Repository
import com.example.todoapp.room.Note
import com.example.todoapp.room.NotesDatabase

class MainActivity : AppCompatActivity() {
    val viewModel : NotesViewModel by lazy {
        val db = NotesDatabase.getDatabase(this)
        val notesRepo = Repository(db)
        val notesFactory = NotesViewModelFactory(notesRepo)
        ViewModelProvider(this,notesFactory)[NotesViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}