package com.example.noteapp.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.Room.NoteRepository

class NoteViewModelFactory(private val noteRepository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java))
        {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(noteRepository) as T
        }
       throw IllegalArgumentException("Error in ViewModel Factory")
    }
}