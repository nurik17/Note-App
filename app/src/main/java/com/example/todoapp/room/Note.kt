package com.example.todoapp.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val noteId : Int = 0,
    @ColumnInfo(name = "title")
    val title : String,
    @ColumnInfo(name = "text")
    val text : String
) : Parcelable