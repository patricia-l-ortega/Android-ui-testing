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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.cognizant.ortegapatricia.brownbagsession_uitesting.data.db.NotesDatabaseHelperImpl
import com.cognizant.ortegapatricia.brownbagsession_uitesting.data.model.Note

class AddNoteActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = NotesDatabaseHelperImpl(this)

        setContent {
            var showDialog by remember { mutableStateOf(false) }

            AddNoteScreen(
                onSaveNote = { title, content ->
                    if (title.isNotBlank() && content.isNotBlank()) {
                        db.insertNote(Note(0, title, content))
                        Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        showDialog = true
                    }
                },
                onBackPressed = { finish() }
            )

            if (showDialog) {
                ShowEmptyFieldsDialog(onDismiss = { showDialog = false })
            }
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

@Composable
fun ShowEmptyFieldsDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Error") },
        text = { Text("Title and Content cannot be empty") },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "Ok",
                    color = Color.Black)
            }
        },
        containerColor = Color.White,
        titleContentColor = Color.Black,
        textContentColor = Color.Black,
        shape = RoundedCornerShape(8.dp)
    )
}