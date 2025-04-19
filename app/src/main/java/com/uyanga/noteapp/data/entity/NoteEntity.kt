package com.uyanga.noteapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uyanga.noteapp.data.model.Note
import java.util.Date

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val timestamp: Date
) {
    fun toNote(): Note = Note(
        id = id,
        title = title,
        description = description,
        timestamp = timestamp
    )

    companion object {
        fun fromNote(note: Note): NoteEntity = NoteEntity(
            id = note.id,
            title = note.title,
            description = note.description,
            timestamp = note.timestamp
        )
    }
}

