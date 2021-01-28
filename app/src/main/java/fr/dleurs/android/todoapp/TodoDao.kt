package fr.dleurs.android.todoapp

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert
    suspend fun insert(todo: Todo): Void;

    @Update
    suspend fun update(todo: Todo): Void;

    @Delete
    suspend fun delete(todo: Todo): Void;

    @Query("DELETE FROM todo_table")
    suspend fun deleteAll();

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAll():Flow<List<Todo>>;
}