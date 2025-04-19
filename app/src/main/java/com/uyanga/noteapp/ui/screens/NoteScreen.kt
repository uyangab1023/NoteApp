package com.uyanga.noteapp.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.Flow
import com.uyanga.noteapp.data.model.Note
import com.uyanga.noteapp.ui.viewmodel.NoteViewModel
import com.uyanga.noteapp.ui.components.NoteItem
import com.uyanga.noteapp.ui.components.NoteDialog

@Composable
fun NoteScreen(
    viewModel: NoteViewModel = viewModel()
) {
    val notes by viewModel.noteList.collectAsState(initial = emptyList())
    var showDialog by remember { mutableStateOf(false) }
    var selectedNote by remember { mutableStateOf<Note?>(null) }
//    var selectedNote: Note? = null

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("JetNote") },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(notes) { note ->
                NoteItem(
                    note = note,
                    onNoteClick = {
                        selectedNote = note
                        showDialog = true
                    },
                    onDeleteClick = {
                        viewModel.deleteNote(note)
                    }
                )
            }
        }

        if (showDialog) {
            NoteDialog(
                note = selectedNote,
                onDismiss = {
                    showDialog = false
                    selectedNote = null
                },
                onSave = { title, description ->
                    if (selectedNote != null) {
                        viewModel.updateNote(selectedNote!!.copy(title = title, description = description))
                    } else {
                        viewModel.addNote(Note(title = title, description = description))
                    }
                    showDialog = false
                    selectedNote = null
                }
            )
        }
    }


}
