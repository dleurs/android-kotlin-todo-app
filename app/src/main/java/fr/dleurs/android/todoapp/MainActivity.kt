package fr.dleurs.android.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val listTodoUi = findViewById<TextView>(R.id.list_todo)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var listTodo:MutableList<Todo> = mutableListOf<Todo>()
        listTodo.add(Todo("Do sport",false))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_add_button -> {
                Toast.makeText(this, "Adding a todo", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menu_delete_button -> {
                Toast.makeText(this, "Deleting a todo", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}