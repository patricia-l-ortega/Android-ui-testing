package com.cognizant.ortegapatricia.brownbagsession_uitesting.jetpackcompose

import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cognizant.ortegapatricia.brownbagsession_uitesting.R
import com.cognizant.ortegapatricia.brownbagsession_uitesting.data.db.NotesDatabaseHelper
import com.cognizant.ortegapatricia.brownbagsession_uitesting.data.model.Note

class HomeActivity : ComponentActivity() {

    private lateinit var db: NotesDatabaseHelper
    private val notes = mutableStateListOf<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = NotesDatabaseHelper(this)
        notes.addAll(db.getAllNotes())

        setContent {
            HomeScreen(
                notes = notes,
                navigateToAddNote = {
                    startActivity(Intent(this, AddNoteActivity::class.java))
                },
                onDeleteNote = { note ->
                    onDeleteNote(note)
                },
                onBackPressed = { finish() }
            )
        }
    }

    private fun onDeleteNote(note: Note) {
        db.deleteNote(note.id)
        notes.remove(note) // Remove the note from the list
        Toast.makeText(this, "Note Deleted", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        notes.clear()
        notes.addAll(db.getAllNotes()) // Refresh the list
    }
}

@Composable
fun HomeScreen(
    notes: List<Note>,
    navigateToAddNote: () -> Unit,
    onDeleteNote: (Note) -> Unit,
    onBackPressed: () -> Unit
) {
    Column {
        ToolbarView(
            title = "My Notes",
            onBackPressed = onBackPressed,
            isRightButtonEnabled = false
        )
        MyNotesView(
            notes = notes,
            onAddNoteClick = navigateToAddNote,
            onNoteClick = { note -> onDeleteNote(note) }
        )
    }
}

@Composable
fun MyNotesView(
    notes: List<Note>,
    onAddNoteClick: () -> Unit,
    onNoteClick: (Note) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                item {
                    NoteListView(
                        notes = notes,
                        onDeleteNote = { note ->
                            onNoteClick(note)
                        }
                    )
                }
            }
        }
        FloatingActionButton(
            onClick = onAddNoteClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color.Black,
            shape = RoundedCornerShape(30.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_add_24),
                contentDescription = "Add Note",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun NoteListView(
    notes: List<Note>,
    onDeleteNote: (Note) -> Unit
) {
    for (note in notes) {
        NoteItemView (
            title = note.title,
            content = note.content,
            onDeleteClick = { onDeleteNote(note) }
        )
    }
}