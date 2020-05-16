package com.example.kotlin

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.*


@RunWith(AndroidJUnit4::class)
class CreateNewNoteTest{

    @Rule @JvmField
    val noteListActivity = ActivityTestRule(NoteListActivity::class.java)

    @Test
    fun createNewNote(){
        val course = DataManager.courses["android_async"]
        val noteTitle = "Test note title"
        val noteText = "This is the body"

        onView(withId(R.id.fab)).perform(click())

        onView(withId(R.id.spCourses)).perform(click())
        onData(allOf(instanceOf(CourseInfo::class.java), equalTo(course))).perform(click())

        onView(withId(R.id.etNoteTitle)).perform(typeText(noteTitle))
        onView(withId(R.id.etNoteText)).perform(typeText(noteText), closeSoftKeyboard())

        pressBack()

        val newNoteInfo = DataManager.notes.last()
        assertEquals(course, newNoteInfo.course)
        assertEquals(noteTitle, newNoteInfo.title)
        assertEquals(noteText, newNoteInfo.text)
    }
}