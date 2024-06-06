package com.ubaya.todoapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ToDoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo: ToDo)

    // MODIFIED 06/06/2024: Added is_done requirement
    @Query("SELECT * FROM todo " +
            "WHERE is_done = 0 " +
            "ORDER BY priority DESC")
    fun selectAllTodo():List<ToDo>

    @Query("SELECT * FROM todo " +
            "WHERE uuid = :id")
    fun selectTodo(id:Int): ToDo

//    @Query("UPDATE todo SET title=:title, note=:note, priority=:priority WHERE uuid = :uuid")
//    fun update(title:String, note:String, priority:Int, uuid:Int)

    @Update
    fun update(todo: ToDo)

    @Delete
    fun deleteTodo(vararg todo: ToDo)
}