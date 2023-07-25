package com.example.todoapp.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NotesViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NotesViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return NotesViewModel(repository) as T
            }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
