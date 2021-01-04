package fr.dleurs.android.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var listTodo:MutableList<Todo> = mutableListOf<Todo>()
        listTodo.add(Todo("Do sport",false))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }
}