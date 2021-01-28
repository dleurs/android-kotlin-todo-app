package fr.dleurs.android.todoapp

import android.app.Application

class TodosApplication : Application() {
    val database by lazy { TodoDatabase.getDatabase(this) }
    val repository by lazy { TodoRepository(database.todoDao()) }
}
