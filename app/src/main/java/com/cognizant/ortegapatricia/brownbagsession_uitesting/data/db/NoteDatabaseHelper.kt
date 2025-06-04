package com.cognizant.ortegapatricia.brownbagsession_uitesting.data.db

import com.cognizant.ortegapatricia.brownbagsession_uitesting.data.model.Note

interface NoteDatabaseHelper {
    fun getAllNotes(): List<Note>
    fun insertNote(note: Note)
    fun deleteNote(noteId: Int)
}