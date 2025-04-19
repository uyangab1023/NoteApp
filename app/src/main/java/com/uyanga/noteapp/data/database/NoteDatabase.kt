package com.uyanga.noteapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.uyanga.noteapp.data.dao.NoteDao
import com.uyanga.noteapp.data.entity.NoteEntity
import com.uyanga.noteapp.util.DateConverter

@Database(entities = [NoteEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}