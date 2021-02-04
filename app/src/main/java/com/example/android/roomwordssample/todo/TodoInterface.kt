package com.example.android.roomwordssample.todo

import com.example.android.roomwordssample.database.TodoRoom
import com.example.android.roomwordssample.mocki.TodoApi

interface TodoInterface {
    fun onItemClick(todoApi: TodoApi);
}