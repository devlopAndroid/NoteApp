package com.example.noteapp.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.noteapp.Room.Note
import com.example.noteapp.Room.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    val allNote : LiveData<List<Note>> = noteRepository.getAllNote.asLiveData()

    fun insert(note: Note) = viewModelScope.launch {
        noteRepository.insertDao(note)
    }
    fun delete(note: Note) = viewModelScope.launch {
        noteRepository.delete(note)
    }
    fun update(note: Note) = viewModelScope.launch {
        noteRepository.update(note)
    }

}