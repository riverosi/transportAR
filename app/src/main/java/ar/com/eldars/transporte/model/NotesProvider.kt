package ar.com.eldars.transporte.model

import androidx.room.Room
import ar.com.eldars.transporte.App

class NotesProvider {
    companion object{
        private val instance = NotesProvider()
        fun getNoteProvider() : NotesProvider {
            return instance
        }
    }

    private var notes = mutableListOf<NoteModel>()
    private var db: NoteAppDatabase = Room.databaseBuilder(
        App.context,
        NoteAppDatabase::class.java, "transport"
    ).allowMainThreadQueries().build()

    init {
        notes = db.notesDao().getAll().toMutableList()
    }

    fun addNote(note : NoteModel) {
        notes.add(note)
        db.notesDao().insert(note)
    }

    fun getAllNotes() : MutableList<NoteModel>{
        return notes
    }

    fun getNote(pos : Int) : NoteModel?{
        return notes?.get(pos)
    }

    fun deleteNote(note: NoteModel){
        notes.remove(note)
        db.notesDao().delete(note)
    }
}