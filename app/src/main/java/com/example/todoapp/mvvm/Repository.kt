package com.example.todoapp.mvvm

import com.example.todoapp.room.Note
import com.example.todoapp.room.NoteDao
import com.example.todoapp.room.NotesDatabase
import kotlinx.coroutines.flow.Flow

class Repository(notesDatabase: NotesDatabase){
    private val noteDao = notesDatabase.noteDao()

    val getAllNotes : Flow<List<Note>> = noteDao.getAllNotes()

    suspend fun upsertNote(note:Note){
        noteDao.upsert(note)
    }
    suspend fun delete(note:Note){
        noteDao.delete(note)
    }
    suspend fun deleteAllNotes(){
        noteDao.deleteAllNote()
    }

}