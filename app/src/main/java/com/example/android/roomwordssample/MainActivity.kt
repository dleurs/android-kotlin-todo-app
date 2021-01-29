/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.roomwordssample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), TodoInterface {

    private val createTodoActivityRequestCode = 1
    private val todoViewModel: TodoViewModel by viewModels {
        TodoViewModelFactory((application as TodosApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = TodoListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        todoViewModel.allTodos.observe(owner = this) { todos ->
            // Update the cached copy of the words in the adapter.
            todos.let { adapter.submitList(it) }
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, createTodoActivityRequestCode)
        }

        val editTextTodo = findViewById<EditText>(R.id.etTodoTitle)
        val buttonTodo = findViewById<Button>(R.id.btnAddTodo)
        buttonTodo.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTextTodo.text)) {
                showToast(applicationContext.getResources().getString(R.string.empty_not_saved))
            } else {
                val todoName = editTextTodo.text.toString()
                todoViewModel.insert(Todo(todoName))
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == createTodoActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(EXTRA_REPLY)?.let { reply ->
                val todo = Todo(reply)
                todoViewModel.insert(todo)
            }
        } else {
            showToast(applicationContext.getResources().getString(R.string.empty_not_saved))
        }
    }

    fun showToast(text: String) {
        Toast.makeText(
                applicationContext,
                text,
                Toast.LENGTH_LONG
        ).show()
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }

    override fun onItemClick(position: Int): Void {
        TODO("Not yet implemented")
    }
}
