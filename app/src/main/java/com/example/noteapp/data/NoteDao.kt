package com.example.noteapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNote(note :Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM Note ORDER BY noteId DESC")
    fun selectNotes() : Flow<List<Note>>

    @Query("SELECT * FROM Note WHERE noteTitle LIKE '%' ||:searchQuery||'%'")
    fun searchInNotesTitle(searchQuery : String) : Flow<List<Note>>

    @Query("DELETE FROM Note")
    suspend fun deleteAllNotes()
}