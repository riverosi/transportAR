package ar.com.eldars.transporte.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.eldars.transporte.R
import ar.com.eldars.transporte.model.NoteModel
import ar.com.eldars.transporte.model.NotesProvider

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private var rowSelectedListener : ((Int)->Unit)? = null

    fun setOnRowSelected(listener: (Int)->Unit){
        rowSelectedListener = listener
    }

    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(note : NoteModel){
            val textView = itemView.findViewById<TextView>(R.id.item_note_title)
            textView.text = note.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemNote = inflater.inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(itemNote)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = NotesProvider.getNoteProvider().getNote(position)
        if (note != null){
            holder.bind(note)
            holder.itemView.setOnClickListener {
                rowSelectedListener?.let {
                    it(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return NotesProvider.getNoteProvider().getAllNotes().count()
    }
}