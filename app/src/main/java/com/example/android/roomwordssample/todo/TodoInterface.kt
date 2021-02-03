package com.example.android.roomwordssample.todo

import com.example.android.roomwordssample.database.Todo

interface TodoInterface {
    fun onItemClick(todo: Todo);
}