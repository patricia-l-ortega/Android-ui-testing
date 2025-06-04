package com.cognizant.ortegapatricia.brownbagsession_uitesting.mock.data

import com.cognizant.ortegapatricia.brownbagsession_uitesting.data.model.Note

object NoteTD {

    val note1 = Note(
        id = 0,
        title = "Test Title 1",
        content = "Test Content 1"
    )

    val note2 = Note(
        id = 0,
        title = "Test Title 2",
        content = "Test Content 2"
    )

    val note3 = Note(
        id = 0,
        title = "Test Title 3",
        content = "Test Content 3"
    )

    val notesList = listOf(note1, note2, note3)
}