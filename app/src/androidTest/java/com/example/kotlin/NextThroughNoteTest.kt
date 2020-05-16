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
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.*


@RunWith(AndroidJUnit4::class)
class NextThroughNoteTest {

    @Rule @JvmField
    val noteListActivity = ActivityTestRule(NoteListActivity::class.java)

    @Test
    fun nextThroughNotes(){

        onData(allOf(instanceOf(NoteInfo::class.java), equalTo(DataManager.notes[0]))).perform(click())

        for(index in 0..DataManager.notes.lastIndex){
            val note = DataManager.notes[index]

            onView(withId(R.id.spCourses)).check(matches(withSpinnerText(note.course?.title)))
            onView(withId(R.id.etNoteTitle)).check(matches(withText(note.title)))
            onView(withId(R.id.etNoteText)).check(matches(withText(note.text)))

            if (index != DataManager.notes.lastIndex){
                onView(allOf(withId(R.id.action_next), isEnabled())).perform(click())
            }

        }

        onView(withId(R.id.action_next)).check(matches(not(isEnabled())))

    }
}