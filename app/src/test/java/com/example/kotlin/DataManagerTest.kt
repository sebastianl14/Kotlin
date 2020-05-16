package com.example.kotlin

import android.provider.ContactsContract
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DataManagerTest {

    @Before
    fun setUp() {
        DataManager.notes.clear()
        DataManager.inicializarNotas()
    }

    @Test
    fun addNote() {

        val course = DataManager.courses.get("android_intens")!!
        val noteTitle = "This is a test note"
        val noteText = "This is the body of my test note"

        val index = DataManager.addNote(course, noteTitle, noteText)
        val note  = DataManager.notes[index]

        assertEquals(course, note.course)
        assertEquals(noteText, note.text)
        assertEquals(noteTitle, note.title)
    }

    @Test
    fun findSimilarNotes(){

        val course = DataManager.courses.get("android_intens")!!
        val noteTitle = "This is a test note"
        val noteText1 = "This is the body of my test note"
        val noteText2 = "This is the secondo text"

        val index1 = DataManager.addNote(course, noteTitle, noteText1)
        val index2 = DataManager.addNote(course, noteTitle, noteText2)

        val noteInfo1 = DataManager.findNote(course, noteTitle, noteText1)
        val foundIndex1 = DataManager.notes.indexOf(noteInfo1)
        assertEquals(index1, foundIndex1)

        val noteInfo2 = DataManager.findNote(course, noteTitle, noteText2)
        val foundIndex2 = DataManager.notes.indexOf(noteInfo2)
        assertEquals(index2, foundIndex2)

    }
}