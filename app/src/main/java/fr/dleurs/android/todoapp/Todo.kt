package fr.dleurs.android.todoapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var done: Boolean = false,
    val authorId: String = "12345"
) {
    constructor(title:String) : this(0,title
    )
};