package com.example.noteapp.repository

import com.example.noteapp.data.Note
import com.example.noteapp.data.NotesDatabase

class NotesRepo(
    notesDatabase: NotesDatabase
) {

    val notesDao = notesDatabase.noteDao

    suspend fun upsertNote(note: Note) = notesDao.upsertNote(note)

    suspend fun deleteNote(note: Note) = notesDao.deleteNote(note)

    fun getNotes() = notesDao.selectNotes()

    fun searchNotes(searchQuery : String) = notesDao.searchInNotesTitle(searchQuery)

    suspend fun deleteAllNotes() = notesDao.deleteAllNotes()
}