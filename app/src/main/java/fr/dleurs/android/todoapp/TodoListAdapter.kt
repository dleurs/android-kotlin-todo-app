package fr.dleurs.android.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter

class TodoListAdapter : ListAdapter<Todo, TodoListAdapter.TodoViewHolder>(TodosComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)
    }

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val todoItemView: TextView = itemView.findViewById(R.id.tvTodoTitle)

        fun bind(text: String?) {
            todoItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): TodoViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.todo_list_item, parent, false)
                return TodoViewHolder(view)
            }
        }
    }

    class TodosComparator : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.name == newItem.name
        }
    }
}
