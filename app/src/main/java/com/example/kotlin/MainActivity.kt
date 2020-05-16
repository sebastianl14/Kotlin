package com.example.kotlin

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val tag = this::class.simpleName //"MainActivity"
    private var notePosition = POSITION_NOT_SET

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        //val dm = DataManager()
        val adapterCourser = ArrayAdapter<CourseInfo>(this,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList())
            //dm.courses.values.toList())
        adapterCourser.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spCourses.adapter = adapterCourser

        //notePosition = intent.getIntExtra(EXTRA_NOTE_POSITION, POSITION_NOT_SET)
        notePosition = savedInstanceState?.getInt(EXTRA_NOTE_POSITION, POSITION_NOT_SET) ?:
            intent.getIntExtra(EXTRA_NOTE_POSITION, POSITION_NOT_SET)

        if (notePosition != POSITION_NOT_SET){
            displayNote()
        } else {
            createNewNote()
        }

        fab.setOnClickListener { view ->
            val originalValue = txtDisplayValue.text.toString().toInt()
            val newValue = originalValue * 2
            txtDisplayValue.text = newValue.toString()
            Snackbar.make(view, "Value $originalValue changed to $newValue", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        Log.d(tag, "onCreate")
    }

    private fun createNewNote() {
        DataManager.notes.add(NoteInfo())
        notePosition = DataManager.notes.lastIndex
    }

    private fun displayNote() {
        if (notePosition > DataManager.notes.lastIndex){
            Log.e(tag, "Invalid note position $notePosition, data manager ${DataManager.notes.lastIndex}")
            return
        }

        val note = DataManager.notes[notePosition]
        etNoteTitle.setText(note.title)
        etNoteText.setText(note.text)

        Log.i(tag, "Display position $notePosition")

        val coursePosition = DataManager.courses.values.indexOf(note.course)
        spCourses.setSelection(coursePosition)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_settings -> true
            R.id.action_next ->{
                moveNext()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveNext() {
        ++notePosition
        displayNote()
        invalidateOptionsMenu()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (notePosition >= DataManager.notes.lastIndex){
            val menuItem = menu?.findItem(R.id.action_next)
            if (menuItem != null){
                menuItem.icon = getDrawable(R.drawable.ic_block_white)
                menuItem.isEnabled = false
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onPause() {
        super.onPause()
        saveNote()
        Log.d(tag, "onPause")

    }

    private fun saveNote() {
        val note = DataManager.notes[notePosition]
        note.title = etNoteTitle.text.toString()
        note.text = etNoteText.text.toString()
        note.course = spCourses.selectedItem as CourseInfo

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(EXTRA_NOTE_POSITION, notePosition)
    }


}
