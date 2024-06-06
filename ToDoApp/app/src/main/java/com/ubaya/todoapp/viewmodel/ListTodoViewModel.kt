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

class ListTodoViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val todoListLD = MutableLiveData<List<ToDo>>()
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val todoLoadingLD = MutableLiveData<Boolean>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh(){
        todoLoadingLD.value = true
        todoLoadErrorLD.value = false

        launch {
            val db = buildDb(getApplication())
            todoListLD.postValue(db.todoDao().selectAllTodo())
            todoLoadingLD.postValue(false)
        }
    }

    fun checkTask(toDo: ToDo){
        launch {
//            val db = buildDb(getApplication())
//            db.todoDao().deleteTodo(toDo)
//            todoListLD.postValue(db.todoDao().selectAllTodo())


            // MODIFIED 06/06/2024: Updated checkTask method to change checked method from delete to update is_done
            toDo.is_done = 1
            buildDb(getApplication()).todoDao().update(toDo)
        }
    }
}