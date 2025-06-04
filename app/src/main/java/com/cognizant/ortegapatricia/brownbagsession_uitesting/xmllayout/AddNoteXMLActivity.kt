package com.cognizant.ortegapatricia.brownbagsession_uitesting.xmllayout

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cognizant.ortegapatricia.brownbagsession_uitesting.R
import com.cognizant.ortegapatricia.brownbagsession_uitesting.data.db.NotesDatabaseHelper
import com.cognizant.ortegapatricia.brownbagsession_uitesting.data.model.Note
import com.cognizant.ortegapatricia.brownbagsession_uitesting.databinding.ActivityAddNoteBinding

class AddNoteXMLActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NotesDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Add Note"
            setDisplayHomeAsUpEnabled(true)
        }

        db = NotesDatabaseHelper(this)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                val title = binding.titleEditText.text.toString()
                val content = binding.contentEditText.text.toString()

                if (title.isNotBlank() && content.isNotBlank()) {
                    val note = Note(0, title, content)
                    db.insertNote(note)
                    finish()
                    Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Title and Content cannot be empty", Toast.LENGTH_SHORT).show()
                }
                true
            }
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_note, menu)
        return true
    }
}