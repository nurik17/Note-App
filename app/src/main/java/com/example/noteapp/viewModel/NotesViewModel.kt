package com.example.noteapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.Note
import com.example.noteapp.repository.NotesRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NotesViewModel(
    private val notesRepo : NotesRepo
) : ViewModel() {

    val notes = notesRepo.getNotes()

    private val _searchNotes = MutableStateFlow<List<Note>>(emptyList())
    val searchNotes : StateFlow<List<Note>> = _searchNotes

    fun upsertNote(note:Note) = viewModelScope.launch{
        notesRepo.upsertNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        notesRepo.deleteNote(note)
    }

    fun deleteAllNotes() = viewModelScope.launch {
        notesRepo.deleteAllNotes()
    }

    fun searchNotes(searchQuery : String) = viewModelScope.launch {
        notesRepo.searchNotes(searchQuery).collect{notesList->
            _searchNotes.emit(notesList)
        }
    }
}