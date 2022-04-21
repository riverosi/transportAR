package ar.com.eldars.transporte.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(NoteModel::class), version = 1)
abstract class NoteAppDatabase : RoomDatabase(){
    abstract fun notesDao(): NotesDao
}
