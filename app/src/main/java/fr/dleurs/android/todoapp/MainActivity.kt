package fr.dleurs.android.todoapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val todoViewModel:TodoViewModel by viewModels {
        WordViewModelFactory((application as TodosApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvTodoList)
        val adapter = TodoListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        todoViewModel.allTodos.observe(owner = this) { todos ->
            // Update the cached copy of the words in the adapter.
            todos.let { adapter.submitList(it) }
        }

        val buttonAddTodo = findViewById<Button>(R.id.btnAddTodo)
        val editTextTodo = findViewById<EditText>(R.id.etTodoTitle)
        buttonAddTodo.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTextTodo.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
                println("Empty todo")
            } else {
                replyIntent.putExtra(EXTRA_REPLY, editTextTodo.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}