package ar.com.eldars.transporte.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import ar.com.eldars.transporte.R
import ar.com.eldars.transporte.model.NoteModel
import ar.com.eldars.transporte.model.NotesProvider

class NotesDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewRoot = inflater.inflate(R.layout.fragment_notes_details, container, false)
        // Inflate the layout for this fragment
        val position = arguments?.getInt("position")
        if (position == null){
            //TODO complete
        }
        var note: NoteModel? = NotesProvider.getNoteProvider().getNote(position!!)

        viewRoot.findViewById<EditText>(R.id.editTextMultiLineTitle).setText(note?.title)
        viewRoot.findViewById<EditText>(R.id.editTextMultiLineComment).setText(note?.description)
        viewRoot.findViewById<Button>(R.id.buttonDelete).setOnClickListener {
            activity?.let { it1 ->
                AlertDialog.Builder(it1)
                    .setTitle("Delete Note")
                    .setMessage("Are you sure to delete this note?")
                    .setPositiveButton("Yes") { _, _ ->
                        if (note != null) {
                            NotesProvider.getNoteProvider().deleteNote(note)
                        }
                        findNavController().navigateUp()
                    }
                    .setNegativeButton("No"){ _, _ ->
                        findNavController().navigateUp()
                    }
                    .show()
            }
        }
        return viewRoot
    }
}