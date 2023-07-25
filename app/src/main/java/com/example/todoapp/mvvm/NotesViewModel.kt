package com.example.todoapp.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.room.Note
import kotlinx.coroutines.launch

class NotesViewModel(private val repository: Repository) : ViewModel() {

    val allNotes = repository.getAllNotes

    fun upsertNote(note: Note){
        viewModelScope.launch {
            repository.upsertNote(note)
        }
    }
    fun delete(note: Note){
        viewModelScope.launch {
            repository.delete(note)
        }
    }
    fun deleteAllNotes(note: Note){
        viewModelScope.launch {
            repository.deleteAllNotes()
        }
    }

}