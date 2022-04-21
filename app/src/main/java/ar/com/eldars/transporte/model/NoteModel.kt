package ar.com.eldars.transporte.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteModel(
    var title: String?,
    var description: String?
    ){
    @PrimaryKey(autoGenerate = true) var nid: Int = 0
}
