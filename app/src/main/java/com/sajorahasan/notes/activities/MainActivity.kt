package com.sajorahasan.notes.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sajorahasan.notes.R
import com.sajorahasan.notes.adapter.NoteAdapter
import com.sajorahasan.notes.db.entity.Note
import com.sajorahasan.notes.viewmodel.NoteViewModal
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val noteViewModel: NoteViewModal by inject()
    private val adapter: NoteAdapter by inject()

    companion object {
        private const val ADD_NOTE_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupButtonAddNote()
        setupRecyclerView()

        noteViewModel.getAllNotes().observe(this, Observer<List<Note>> {
            it?.let { adapter.setNotes(it) }
        })
    }

    private fun setupRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter
    }

    private fun setupButtonAddNote() {
        buttonAddNote.setOnClickListener {
            startActivityForResult(
                Intent(this, AddNoteActivity::class.java),
                ADD_NOTE_REQUEST
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.delete_all_notes -> {
                noteViewModel.deleteAllNotes()
                Toast.makeText(this, "All notes deleted!", Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val newNote = Note(
                data.getStringExtra(AddNoteActivity.EXTRA_TITLE)!!,
                data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION)!!
            )
            noteViewModel.insert(newNote)

            Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Note not saved!", Toast.LENGTH_SHORT).show()
        }
    }
}
