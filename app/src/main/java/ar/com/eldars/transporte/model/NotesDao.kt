package ar.com.eldars.transporte.model

import androidx.room.*

@Dao
interface NotesDao {
    //language=sql
    @Query("SELECT * FROM Notes")
    fun getAll(): List<NoteModel>

    @Query("SELECT * FROM Notes WHERE nid =:noteId LIMIT 1")
    fun getById(noteId : Int): NoteModel

    @Insert
    fun insert(vararg notes: NoteModel)

    @Delete
    fun delete(note: NoteModel)

    @Update
    fun update(note: NoteModel)
}