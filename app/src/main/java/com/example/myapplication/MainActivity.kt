//package com.example.myapplication
//
//import android.content.Intent
//import android.app.Dialog
//import android.content.SharedPreferences
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.ListView
//import android.widget.SearchView
//import androidx.activity.ComponentActivity
//
//class MainActivity : ComponentActivity() {
//    private lateinit var noteInput: EditText
//    private lateinit var addNoteButton: Button
//    private lateinit var searchView: SearchView
//    private lateinit var notesList: ListView
//    private lateinit var adapter: NoteAdapter
//    private var notes = mutableListOf<Note>()
//    private lateinit var sharedPreferences: SharedPreferences
//    private lateinit var username: String
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        // Retrieve username from intent
//        username = intent.getStringExtra("username") ?: "Guest"
//        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
//
//        noteInput = findViewById(R.id.note_input)
//        addNoteButton = findViewById(R.id.add_note_button)
//        searchView = findViewById(R.id.search_view)
//        notesList = findViewById(R.id.notes_list)
//
//        // Load notes for the user
//        loadUserNotes()
//
//        adapter = NoteAdapter(this, notes)
//        notesList.adapter = adapter
//
//
//        addNoteButton.setOnClickListener {
//            val noteText = noteInput.text.toString()
//            if (noteText.isNotEmpty()) {
//                val title = noteText.substringBefore("\n").takeIf { it.isNotEmpty() } ?: "Untitled"
//                val body = noteText.substringAfter("\n", "").takeIf { it.isNotEmpty() } ?: "No content"
//                notes.add(Note(title, body))
//                adapter.notifyDataSetChanged()
//                noteInput.text.clear()
//                saveUserNotes() // Save notes after adding
//            }
//        }
//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                adapter.filter()
//                return true
//            }
//        })
//
//        // Handle note item clicks for editing
//        notesList.setOnItemClickListener { parent, view, position, id ->
//            showEditDialog(notes[position], position)
//        }
//
//        val logoutButton: Button = findViewById(R.id.logout_button)
//        logoutButton.setOnClickListener {
//            logout()
//        }
//    }
//
//    private fun loadUserNotes() {
//        val userNotes = sharedPreferences.getStringSet(username, emptySet()) ?: emptySet()
//        notes.clear()
//        userNotes.forEach { noteString ->
//            val parts = noteString.split("::")
//            if (parts.size == 2) {
//                notes.add(Note(parts[0], parts[1]))
//            }
//        }
//    }
//
//    private fun logout() {
//        sharedPreferences.edit().clear().apply()
//        val intent = Intent(this, LoginActivity::class.java)
//        startActivity(intent)
//        finish()
//    }
//
//    private fun saveUserNotes() {
//        val userNotesSet = notes.map { "${it.title}::${it.body}" }.toSet()
//        sharedPreferences.edit().putStringSet(username, userNotesSet).apply()
//    }
//
//    private fun showEditDialog(note: Note, position: Int) {
//        val dialog = Dialog(this)
//        dialog.setContentView(R.layout.dialog_edit_note)
//
//        val editTitle = dialog.findViewById<EditText>(R.id.edit_note_title)
//        val editBody = dialog.findViewById<EditText>(R.id.edit_note_body)
//        val saveButton = dialog.findViewById<Button>(R.id.save_button)
//
//        editTitle.setText(note.title)
//        editBody.setText(note.body)
//
//        saveButton.setOnClickListener {
//            // Update the note with new values
//            note.title = editTitle.text.toString()
//            note.body = editBody.text.toString()
//
//            // Update the note in the list
//            notes[position] = note
//
//            adapter.notifyDataSetChanged() // Notify the adapter of data change
//            saveUserNotes() // Save the updated notes
//            dialog.dismiss() // Close the dialog
//        }
//
//        dialog.show() // Show the dialog
//    }
//}
//


//
//package com.example.myapplication
//
//import android.content.Intent
//import android.app.Dialog
//import android.content.SharedPreferences
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.ListView
//import android.widget.SearchView
//import androidx.activity.ComponentActivity
//
//class MainActivity : ComponentActivity() {
//    private lateinit var noteInput: EditText
//    private lateinit var addNoteButton: Button
//    private lateinit var searchView: SearchView
//    private lateinit var notesList: ListView
//    private lateinit var adapter: NoteAdapter
//    private var notes = mutableListOf<Note>()
//    private lateinit var sharedPreferences: SharedPreferences
//    private lateinit var username: String
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        username = intent.getStringExtra("username") ?: "Guest"
//        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
//
//        noteInput = findViewById(R.id.note_input)
//        addNoteButton = findViewById(R.id.add_note_button)
//        searchView = findViewById(R.id.search_view)
//        notesList = findViewById(R.id.notes_list)
//
//        loadUserNotes()
//
//        adapter = NoteAdapter(this, notes)
//        notesList.adapter = adapter
//
//        addNoteButton.setOnClickListener {
//            val noteText = noteInput.text.toString()
//            if (noteText.isNotEmpty()) {
//                val title = noteText.substringBefore("\n").takeIf { it.isNotEmpty() } ?: "Untitled"
//                val body = noteText.substringAfter("\n", "").takeIf { it.isNotEmpty() } ?: "No content"
//                notes.add(Note(title, body))
//                adapter.updateNotes(notes)
//                noteInput.text.clear()
//                saveUserNotes()
//            }
//        }
//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                adapter.filter.filter(newText)
//                return true
//            }
//        })
//
//        notesList.setOnItemClickListener { _, _, position, _ ->
//            showEditDialog(notes[position], position)
//        }
//
//        val logoutButton: Button = findViewById(R.id.logout_button)
//        logoutButton.setOnClickListener {
//            logout()
//        }
//    }
//
//    private fun loadUserNotes() {
//        val userNotes = sharedPreferences.getStringSet(username, emptySet()) ?: emptySet()
//        notes.clear()
//        userNotes.forEach { noteString ->
//            val parts = noteString.split("::")
//            if (parts.size == 2) {
//                notes.add(Note(parts[0], parts[1]))
//            }
//        }
//        if (::adapter.isInitialized) {
//            adapter.updateNotes(notes)
//        }
//    }
//
//    private fun logout() {
//        sharedPreferences.edit().clear().apply()
//        val intent = Intent(this, LoginActivity::class.java)
//        startActivity(intent)
//        finish()
//    }
//
//    private fun saveUserNotes() {
//        val userNotesSet = notes.map { "${it.title}::${it.body}" }.toSet()
//        sharedPreferences.edit().putStringSet(username, userNotesSet).apply()
//    }
//
//    private fun showEditDialog(note: Note, position: Int) {
//        val dialog = Dialog(this)
//        dialog.setContentView(R.layout.dialog_edit_note)
//
//        val editTitle = dialog.findViewById<EditText>(R.id.edit_note_title)
//        val editBody = dialog.findViewById<EditText>(R.id.edit_note_body)
//        val saveButton = dialog.findViewById<Button>(R.id.save_button)
//
//        editTitle.setText(note.title)
//        editBody.setText(note.body)
//
//        saveButton.setOnClickListener {
//            note.title = editTitle.text.toString()
//            note.body = editBody.text.toString()
//            notes[position] = note
//            adapter.updateNotes(notes)
//            saveUserNotes()
//            dialog.dismiss()
//        }
//
//        dialog.show()
//    }
//}

package com.example.myapplication

import android.content.Intent
import android.app.Dialog
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.SearchView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var noteInput: EditText
    private lateinit var addNoteButton: Button
    private lateinit var searchView: SearchView
    private lateinit var notesList: ListView
    private lateinit var adapter: NoteAdapter
    private var notes = mutableListOf<Note>()
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = intent.getStringExtra("username") ?: "Guest"
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        noteInput = findViewById(R.id.note_input)
        addNoteButton = findViewById(R.id.add_note_button)
        searchView = findViewById(R.id.search_view)
        notesList = findViewById(R.id.notes_list)

        loadUserNotes()
        adapter = NoteAdapter(this, notes)
        notesList.adapter = adapter

        addNoteButton.setOnClickListener {
            val noteText = noteInput.text.toString()
            if (noteText.isNotEmpty()) {
                val title = noteText.substringBefore("\n").takeIf { it.isNotEmpty() } ?: "Untitled"
                val body = noteText.substringAfter("\n", "").takeIf { it.isNotEmpty() } ?: "No content"
                notes.add(Note(title, body))
                adapter.updateNotes(notes)
                noteInput.text.clear()
                saveUserNotes()
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })

        notesList.setOnItemClickListener { _, _, position, _ ->
            showEditDialog(notes[position], position)
        }

        val logoutButton: Button = findViewById(R.id.logout_button)
        logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun loadUserNotes() {
        val userNotes = sharedPreferences.getStringSet(username, emptySet()) ?: emptySet()
        notes.clear()
        userNotes.forEach { noteString ->
            val parts = noteString.split("::")
            if (parts.size == 2) {
                notes.add(Note(parts[0], parts[1]))
            }
        }
        if (::adapter.isInitialized) {
            adapter.updateNotes(notes)
        }
    }

    private fun logout() {
        sharedPreferences.edit().remove(username).apply()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun saveUserNotes() {
        val userNotesSet = notes.map { "${it.title}::${it.body}" }.toSet()
        sharedPreferences.edit().putStringSet(username, userNotesSet).apply()
    }

    private fun showEditDialog(note: Note, position: Int) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_edit_note)

        val editTitle = dialog.findViewById<EditText>(R.id.edit_note_title)
        val editBody = dialog.findViewById<EditText>(R.id.edit_note_body)
        val saveButton = dialog.findViewById<Button>(R.id.save_button)

        editTitle.setText(note.title)
        editBody.setText(note.body)

        saveButton.setOnClickListener {
            note.title = editTitle.text.toString()
            note.body = editBody.text.toString()
            notes[position] = note
            adapter.updateNotes(notes)
            saveUserNotes()
            dialog.dismiss()
        }

        dialog.show()
    }
}
