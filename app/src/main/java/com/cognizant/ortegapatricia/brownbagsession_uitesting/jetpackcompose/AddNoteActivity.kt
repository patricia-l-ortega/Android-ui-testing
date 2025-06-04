package com.cognizant.ortegapatricia.brownbagsession_uitesting.jetpackcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.cognizant.ortegapatricia.brownbagsession_uitesting.data.db.NotesDatabaseHelper
import com.cognizant.ortegapatricia.brownbagsession_uitesting.data.model.Note

class AddNoteActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = NotesDatabaseHelper(this)

        setContent {
            AddNoteScreen(
                onSaveNote = { title, content ->
                    if (title.isNotBlank() && content.isNotBlank()) {
                        db.insertNote(Note(0, title, content))
                        Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Title and Content cannot be empty", Toast.LENGTH_SHORT).show()
                    }
                },
                onBackPressed = { finish() }
            )
        }
    }
}

@Composable
fun AddNoteScreen(
    onSaveNote: (String, String) -> Unit,
    onBackPressed: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Column {
        ToolbarView(
            title = "Add Note",
            onBackPressed = onBackPressed,
            onRightButtonClick = {
                onSaveNote(title, content)
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Content") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }

    }
}