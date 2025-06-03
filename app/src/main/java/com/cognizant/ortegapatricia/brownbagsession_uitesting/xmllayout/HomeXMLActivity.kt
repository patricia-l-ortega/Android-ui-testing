package com.cognizant.ortegapatricia.brownbagsession_uitesting.xmllayout

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cognizant.ortegapatricia.brownbagsession_uitesting.NotesAdapter
import com.cognizant.ortegapatricia.brownbagsession_uitesting.data.db.NotesDatabaseHelper
import com.cognizant.ortegapatricia.brownbagsession_uitesting.databinding.ActivityHomescreenBinding

class HomeXMLActivity : ComponentActivity() {

    private lateinit var binding: ActivityHomescreenBinding
    private lateinit var db: NotesDatabaseHelper
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomescreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)
        notesAdapter = NotesAdapter(db.getAllNotes(), this)

        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.notesRecyclerView.adapter = notesAdapter

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes())
    }
}