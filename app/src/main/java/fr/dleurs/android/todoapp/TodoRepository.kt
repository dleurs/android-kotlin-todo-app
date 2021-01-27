package fr.dleurs.android.todoapp

class TodoRepository {
    private val _initTodos: List<Todo> = listOf(
        Todo("Do sport", false),
        Todo("Meditate", false),
        Todo("Cook perlmenis", true),
        Todo("Learn russian", false)
    )

    val todos = mutableListOf<Todo>()

    init {
        todos.addAll(_initTodos)
    }
}