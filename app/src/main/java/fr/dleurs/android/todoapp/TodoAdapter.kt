package fr.dleurs.android.todoapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_list_item.view.*

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.todo_list_item,
                parent,
                false
            )
        )
    }

    fun addTodo(todo:Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteTodo(position: Int) {
        todos.removeAt(position)
        notifyItemRemoved(position)
    }

    fun deleteDoneTodos() {
        todos.removeAll {
            todo -> todo.done
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        println("Clicking on todo")
        if (isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        holder.itemView.apply {
            tvTodoTitle.text = currentTodo.name
            cbTodoDone.isChecked = currentTodo.done
            toggleStrikeThrough(tvTodoTitle, currentTodo.done)
            cbTodoDone.setOnCheckedChangeListener{ _, done ->
                toggleStrikeThrough(tvTodoTitle, done)
                currentTodo.done = !currentTodo.done
            }

            ibTodoDelete.setOnClickListener {
                deleteTodo(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}