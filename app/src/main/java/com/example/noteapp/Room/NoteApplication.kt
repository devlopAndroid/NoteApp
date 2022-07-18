package com.example.noteapp.Room

import android.app.Application

class NoteApplication : Application(){

    private val database by lazy {
        NoteDatabase.getDatabase((this@NoteApplication))
    }
    val repository by lazy {
        NoteRepository(database.noteDao())
    }
}