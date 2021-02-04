package com.example.android.roomwordssample

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

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.roomwordssample.database.TodoRoom
import com.example.android.roomwordssample.database.TodoDao
import com.example.android.roomwordssample.database.TodoDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4::class)
class TodoRoomApiDaoTest {

    private lateinit var todoDao: TodoDao
    private lateinit var db: TodoDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, TodoDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        todoDao = db.todoDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetWord() = runBlocking {
        val todo = TodoRoom("word")
        todoDao.insert(todo)
        val allTodos = todoDao.getTodos().first()
        assertEquals(allTodos[0].name, todo.name)
    }

    @Test
    @Throws(Exception::class)
    fun getAllWords() = runBlocking {
        val todo = TodoRoom("aaa")
        todoDao.insert(todo)
        val todo2 = TodoRoom("bbb")
        todoDao.insert(todo2)
        val allTodoRooms: List<TodoRoom> = todoDao.getTodos().first()
        assertEquals(allTodoRooms[0].name, todo.name)
        assertEquals(allTodoRooms[1].name, todo2.name)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAll() = runBlocking {
        val todo = TodoRoom("word")
        todoDao.insert(todo)
        val todo2 = TodoRoom("word2")
        todoDao.insert(todo2)
        todoDao.deleteAll()
        val allWords = todoDao.getTodos().first()
        assertTrue(allWords.isEmpty())
    }
}
