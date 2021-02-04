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

import androidx.lifecycle.*
import com.example.android.roomwordssample.database.TodoRoom
import com.example.android.roomwordssample.database.TodoRepository
import com.example.android.roomwordssample.mocki.TodoApi
import com.example.android.roomwordssample.mocki.TodoApiService
import com.example.android.roomwordssample.mocki.TodoRetrofitApi
import kotlinx.coroutines.launch

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */

class TodoViewModel(private val repositoryRoom: TodoRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.

    private val _response = MutableLiveData<List<TodoApi>>()

    // The external immutable LiveData for the response String
    val allTodosApi: LiveData<List<TodoApi>>
        get() = _response

    init {
        getTodosApi()
    }

    private fun getTodosApi() {
        var todoList = mutableListOf<TodoApi>()
        viewModelScope.launch {
            try {
                val listResult = TodoRetrofitApi.retrofitService.getTodos()
                todoList.add(TodoApi("0", "Success: ${listResult.size} todos retrieved", false))
                _response.value = todoList
            } catch (e: Exception) {
                todoList.add( TodoApi("0","Failure: ${e.message}", false))
                _response.value = todoList
            }
        }
    }

    fun insertApi(todoRoom: TodoRoom) = viewModelScope.launch {}
    fun deleteApi(todoRoom: TodoRoom) = viewModelScope.launch {}
    /*

    val allTodos: LiveData<List<Todo>> = repositoryRoom.allTodos.asLiveData()


    fun insert(todoRoom: TodoRoom) = viewModelScope.launch {
        repositoryRoom.insert(todoRoom)
    }

    fun delete(todoRoom: TodoRoom) = viewModelScope.launch {
        repositoryRoom.delete(todoRoom)
    }
    */

}

class TodoViewModelFactory(private val repository: TodoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
