package ar.com.eldars.transporte.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.eldars.transporte.R
import ar.com.eldars.transporte.adapters.NotesAdapter
import ar.com.eldars.transporte.model.NotesProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotesListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_notes_list, container, false)
        rootView.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_notesListFragment_to_notesAddFragment)
        )

        val recyclerNote = rootView.findViewById<RecyclerView>(R.id.recycleNote)
        recyclerNote.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,
            false)

        val adapter = NotesAdapter()
        recyclerNote.adapter = adapter

        adapter.setOnRowSelected { position ->
            val bundle = Bundle()
            bundle.putInt("position", position)
            findNavController().navigate(
                R.id.action_notesListFragment_to_notesDetailsFragment,
                bundle)
        }

        NotesProvider.getNoteProvider().getAllNotes().forEach{
            Log.d(it.title.toString(), it.description.toString())
        }

        return rootView
    }
}