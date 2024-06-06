package com.ubaya.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.todoapp.databinding.TodoItemLayoutBinding
import com.ubaya.todoapp.model.ToDo

class TodoListAdapter (val todoList: ArrayList<ToDo>, val adapterOnClick: (ToDo) -> Unit)
    : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(), TodoCheckedChangeListener, TodoEditClickListener {
    class TodoViewHolder(var binding: TodoItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = TodoItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int)
    {

        holder.binding.todo = todoList[position]
        holder.binding.listener = this
        holder.binding.editListener = this

//        holder.binding.checkTask.text = todoList[position].title
//        holder.binding.checkTask.isChecked = false
//
//        holder.binding.checkTask.setOnCheckedChangeListener{compoundButton, b ->
//
//            if(compoundButton.isPressed) {
//                adapterOnClick(todoList[position])
//            }
//
//        }
//
//        holder.binding.imgEdit.setOnClickListener{
//            val action = ToDoListFragmentDirections.actionToDoListFragmentToEditTodoFragment(
//                todoList[position].uuid
//            )
//            Navigation.findNavController(it).navigate(action)
//        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateTodoList(newTodoList: List<ToDo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onTodoCheckedChange(cb: CompoundButton, isChecked: Boolean, todo: ToDo) {
        if(cb.isPressed){
            adapterOnClick(todo)
        }
    }

    override fun onTodoEditClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = ToDoListFragmentDirections.actionToDoListFragmentToEditTodoFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }

}
