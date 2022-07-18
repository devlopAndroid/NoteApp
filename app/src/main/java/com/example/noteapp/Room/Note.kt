package com.example.noteapp.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("note_table")
data class Note(
    @ColumnInfo(name = "Title") val title : String,
    @ColumnInfo(name = "Discription") val discription : String,
    @ColumnInfo(name = "timestamp") val timestamp : String
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}
