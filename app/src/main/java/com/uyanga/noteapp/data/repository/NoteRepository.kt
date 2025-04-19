package com.uyanga.noteapp.data.repository

import com.uyanga.noteapp.data.dao.NoteDao
import com.uyanga.noteapp.data.entity.NoteEntity
import com.uyanga.noteapp.data.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) {
    fun getAllNotes(): Flow<List<Note>> =
        noteDao.getAllNotes().map { entities ->
            entities.map { it.toNote() }
        }

    suspend fun addNote(note: Note) {
        noteDao.insertNote(NoteEntity.fromNote(note))
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(NoteEntity.fromNote(note))
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(NoteEntity.fromNote(note))
    }
}