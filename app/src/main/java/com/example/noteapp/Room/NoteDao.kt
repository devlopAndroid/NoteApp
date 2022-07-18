package com.example.noteapp.Room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(note: Note)

    @Delete
    suspend fun deleteData(note: Note)

    @Update
    suspend fun updateData(note: Note)

    @Query("SELECT * FROM note_table ORDER BY ID ASC ")
    fun getNoteData() : Flow<List<Note>>
}