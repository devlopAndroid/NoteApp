package com.example.noteapp.Room

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    val getAllNote : Flow<List<Note>> = noteDao.getNoteData()

    @WorkerThread
    suspend fun insertDao(note : Note)
    {
        noteDao.insertData(note)
    }
    suspend fun delete(note: Note)
    {
        noteDao.deleteData(note)
    }
    suspend fun update(note: Note)
    {
        noteDao.updateData(note)
    }
}