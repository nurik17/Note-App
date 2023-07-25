package com.example.todoapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("Select * from notes Order By noteId ASC")
    fun getAllNotes() : Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // if existing note update it
    suspend fun upsert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Delete from notes")
    suspend fun deleteAllNote()
}