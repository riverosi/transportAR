package ar.com.eldars.transporte.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import ar.com.eldars.transporte.R
import ar.com.eldars.transporte.model.NoteModel
import ar.com.eldars.transporte.model.NotesProvider

class NotesAddFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_notes_add, container, false)
        val commentaryEditText = rootView.findViewById<EditText>(R.id.editTextCommentary)
        val titleEditText = rootView.findViewById<EditText>(R.id.editTextTitle)
        rootView.findViewById<Button>(R.id.buttonAddNote).setOnClickListener {
            val commentary = commentaryEditText.text.toString()
            val title = titleEditText.text.toString()
            val note = NoteModel(title,commentary)
            NotesProvider.getNoteProvider().addNote(note)
            findNavController().navigateUp()
        }
        return rootView
    }
}