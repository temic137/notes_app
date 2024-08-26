//package com.example.myapplication
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ArrayAdapter
//import android.widget.Filter
//import android.widget.Filterable
//import android.widget.TextView
//import android.widget.Button
//import android.widget.Toast
//
//
//
//class NoteAdapter(context: Context, private var notes: MutableList<Note>) : ArrayAdapter<Note>(context, 0, notes), Filterable {
//    private var originalNotes: List<Note> = notes
//    private var filteredNotes: List<Note> = notes
//
//    override fun getCount(): Int {
//        return filteredNotes.size
//    }
//
//    override fun getItem(position: Int): Note {
//        return filteredNotes[position]
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val note = getItem(position)
//        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)
//
//        val titleTextView = view.findViewById<TextView>(R.id.note_title)
//        val bodyTextView = view.findViewById<TextView>(R.id.note_body)
//        val deleteButton = view.findViewById<Button>(R.id.delete_button)
//
//        titleTextView.text = note.title
//        bodyTextView.text = note.body
//
//        // Set up delete button click listener
//        deleteButton.setOnClickListener {
//            notes.removeAt(position) // Remove the note from the list
//            notifyDataSetChanged() // Notify the adapter of the change
//            Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()
//        }
//
//        return view
//    }
//
//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val filteredNotes = if (constraint.isNullOrEmpty()) {
//                    originalNotes
//                } else {
//                    originalNotes.filter { note ->
//                        note.title.contains(constraint, ignoreCase = true) ||
//                                note.body.contains(constraint, ignoreCase = true)
//                    }
//                }
//                return FilterResults().apply { values = filteredNotes }
//            }
//
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                filteredNotes = if (results?.values is List<*>) {
//                    results.values as List<Note>
//                } else {
//                    originalNotes
//                }
//                notifyDataSetChanged()
//            }
//        }
//    }
//
//    fun filter():Filter {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val filteredNotes = if (constraint.isNullOrEmpty()) {
//                    originalNotes
//                } else {
//                    originalNotes.filter { note ->
//                        note.title.contains(constraint, ignoreCase = true) ||
//                                note.body.contains(constraint, ignoreCase = true)
//                    }
//                }
//                return FilterResults().apply { values = filteredNotes }
//            }
//
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                filteredNotes = if (results?.values is List<*>) {
//                    results.values as List<Note>
//                } else {
//                    originalNotes
//                }
//                notifyDataSetChanged()
//            }
//        }
//    }
//}


//
//package com.example.myapplication
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ArrayAdapter
//import android.widget.Filter
//import android.widget.Filterable
//import android.widget.TextView
//import android.widget.Toast
//import android.widget.ImageView
//
//class NoteAdapter(context: Context, private var notes: MutableList<Note>) : ArrayAdapter<Note>(context, 0, notes), Filterable {
//    private var originalNotes: List<Note> = notes
//    private var filteredNotes: List<Note> = notes
//
//    override fun getCount(): Int {
//        return filteredNotes.size
//    }
//
//    override fun getItem(position: Int): Note {
//        return filteredNotes[position]
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val note = getItem(position)
//        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)
//
//        val titleTextView = view.findViewById<TextView>(R.id.note_title)
//        val bodyTextView = view.findViewById<TextView>(R.id.note_body)
//        val deleteButton = view.findViewById<ImageView>(R.id.delete_button)
//
//        titleTextView.text = note.title
//        bodyTextView.text = note.body
//
//        // Set up delete button click listener
//        deleteButton.setOnClickListener {
//            notes.removeAt(position) // Remove the note from the list
//            notifyDataSetChanged() // Notify the adapter of the change
//            Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()
//        }
//
//        return view
//    }
//
//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val filteredNotes = if (constraint.isNullOrEmpty()) {
//                    originalNotes
//                } else {
//                    originalNotes.filter { note ->
//                        note.title.contains(constraint, ignoreCase = true) ||
//                                note.body.contains(constraint, ignoreCase = true)
//                    }
//                }
//                return FilterResults().apply { values = filteredNotes }
//            }
//
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                filteredNotes = if (results?.values is List<*>) {
//                    results.values as List<Note>
//                } else {
//                    originalNotes
//                }
//                notifyDataSetChanged()
//            }
//        }
//    }
//
//    fun filter():Filter {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val filteredNotes = if (constraint.isNullOrEmpty()) {
//                    originalNotes
//                } else {
//                    originalNotes.filter { note ->
//                        note.title.contains(constraint, ignoreCase = true) ||
//                                note.body.contains(constraint, ignoreCase = true)
//                    }
//                }
//                return FilterResults().apply { values = filteredNotes }
//            }
//
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                filteredNotes = if (results?.values is List<*>) {
//                    results.values as List<Note>
//                } else {
//                    originalNotes
//                }
//                notifyDataSetChanged()
//            }
//        }
//    }
//}

package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class NoteAdapter(context: Context, private var notes: MutableList<Note>) : ArrayAdapter<Note>(context, 0, notes), Filterable {
    private var originalNotes: MutableList<Note> = ArrayList(notes)
    private var filteredNotes: MutableList<Note> = ArrayList(notes)

    override fun getCount(): Int {
        return filteredNotes.size
    }

    override fun getItem(position: Int): Note {
        return filteredNotes[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val note = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)

        val titleTextView = view.findViewById<TextView>(R.id.note_title)
        val bodyTextView = view.findViewById<TextView>(R.id.note_body)
        val deleteButton = view.findViewById<ImageView>(R.id.delete_button)

        titleTextView.text = note.title
        bodyTextView.text = note.body

        // Set up delete button click listener
        deleteButton.setOnClickListener {
            originalNotes.remove(note) // Remove the note from the original list
            filteredNotes.removeAt(position) // Remove the note from the filtered list
            notifyDataSetChanged() // Notify the adapter of the change
            Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.lowercase() ?: ""
                val filtered = if (query.isEmpty()) {
                    originalNotes
                } else {
                    originalNotes.filter { note ->
                        note.title.lowercase().contains(query) ||
                                note.body.lowercase().contains(query)
                    }.toMutableList()
                }

                return FilterResults().apply { values = filtered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredNotes = if (results?.values is List<*>) {
                    results.values as MutableList<Note>
                } else {
                    originalNotes
                }
                notifyDataSetChanged()
            }
        }
    }

    fun updateNotes(newNotes: MutableList<Note>) {
        originalNotes = ArrayList(newNotes)
        filteredNotes = ArrayList(newNotes)
        notifyDataSetChanged()
    }
}
