package com.ubaya.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.ubaya.todoapp.model.ToDo
import com.ubaya.todoapp.model.ToDoDatabase
import com.ubaya.todoapp.util.buildDb
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {

    private var job = Job()
    val todoLD = MutableLiveData<ToDo>()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun addTodo(toDo: ToDo){
        launch {
            val db = buildDb(getApplication())
            db.todoDao().insertAll(toDo)
        }
    }

    fun fetch(uuid: Int){
        launch{
            todoLD.postValue(buildDb(getApplication()).todoDao().selectTodo(uuid))
        }
    }

    fun update(toDo: ToDo){
        launch{
            buildDb(getApplication()).todoDao().update(toDo)
        }
    }
}