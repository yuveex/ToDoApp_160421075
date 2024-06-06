package com.ubaya.todoapp.view

import android.view.View
import android.widget.CompoundButton
import com.ubaya.todoapp.model.ToDo

interface TodoCheckedChangeListener{
    fun onTodoCheckedChange(cb: CompoundButton, isChecked: Boolean, todo: ToDo){

    }
}

interface TodoEditClickListener {
    fun onTodoEditClick(view: View){

    }
    fun onTodoSaveChangesClick(view: View){

    }
}

interface RadioClickListener {
    fun onRadioClick(view:View){

    }
}
