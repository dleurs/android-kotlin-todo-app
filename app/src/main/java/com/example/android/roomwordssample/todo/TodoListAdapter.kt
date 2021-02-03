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

package com.example.android.roomwordssample.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.roomwordssample.R
import com.example.android.roomwordssample.database.Todo

class TodoListAdapter(private val todoInterface: TodoInterface) : ListAdapter<Todo, TodoListAdapter.TodoViewHolder>(
    TODOS_COMPARATOR
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
           return TodoViewHolder.create(parent, todoInterface)
    }

  override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
    val current = getItem(position)
    holder.bind(current)

  }

    class TodoViewHolder(itemView: View, private val todoInterface: TodoInterface) : RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView = itemView.findViewById(R.id.todo_list_item_text)
        private val deleteTodo: ImageButton = itemView.findViewById(R.id.imageButton)

        var data: Todo? = null

      init {
       deleteTodo.setOnClickListener {
         data?.let {
           todoInterface.onItemClick(it)
         }
        }
    }
        
          fun bind(item: Todo?) {
      // assign it to `data` so it can be used with `setOnClickListener`
      data = item
      wordItemView.text = item?.name

    }

        companion object {
            fun create(parent: ViewGroup,todoInterface: TodoInterface): TodoViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return TodoViewHolder(view,todoInterface)
            }
        }
    }

    companion object {
        private val TODOS_COMPARATOR = object : DiffUtil.ItemCallback<Todo>() {
            override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}
