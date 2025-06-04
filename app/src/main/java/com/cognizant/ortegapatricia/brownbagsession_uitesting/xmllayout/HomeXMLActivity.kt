package com.cognizant.ortegapatricia.brownbagsession_uitesting.xmllayout

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cognizant.ortegapatricia.brownbagsession_uitesting.NotesAdapter
import com.cognizant.ortegapatricia.brownbagsession_uitesting.data.db.NotesDatabaseHelper
import com.cognizant.ortegapatricia.brownbagsession_uitesting.databinding.ActivityHomescreenBinding

class HomeXMLActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomescreenBinding
    private lateinit var db: NotesDatabaseHelper
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomescreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "My Notes"
            setDisplayHomeAsUpEnabled(true)
        }

        db = NotesDatabaseHelper(this)
        notesAdapter = NotesAdapter(db.getAllNotes(), this)

        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.notesRecyclerView.adapter = notesAdapter

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddNoteXMLActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}