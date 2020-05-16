package com.example.kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.android.synthetic.main.content_note_list.*

class NoteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

//        listNotes.adapter = ArrayAdapter(this,
//            android.R.layout.simple_expandable_list_item_1,
//            DataManager.notes)
//
//        listNotes.setOnItemClickListener{parent, view, position, id ->
//            val intent = Intent(this, MainActivity::class.java)
//            intent.putExtra(EXTRA_NOTE_POSITION, position)
//            startActivity(intent)
//        }

        listItems.layoutManager = LinearLayoutManager(this)
        listItems.adapter = NoteRecyclerAdapter(this, DataManager.notes)
    }

    override fun onResume() {
        super.onResume()
        listItems.adapter?.notifyDataSetChanged()
//        (listNotes.adapter as ArrayAdapter<NoteInfo>).notifyDataSetChanged()
    }

}
