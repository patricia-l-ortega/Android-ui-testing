package com.cognizant.ortegapatricia.brownbagsession_uitesting.data.db

import com.cognizant.ortegapatricia.brownbagsession_uitesting.data.model.Note

class FakeNotesDatabaseHelper : NoteDatabaseHelper {

    private val notes = mutableListOf<Note>()

    override fun getAllNotes(): List<Note> {
        return notes.toList()
    }

    override fun insertNote(note: Note) {
        notes.add(note)
    }

    override fun deleteNote(noteId: Int) {
        notes.removeIf { it.id == noteId }
    }
}