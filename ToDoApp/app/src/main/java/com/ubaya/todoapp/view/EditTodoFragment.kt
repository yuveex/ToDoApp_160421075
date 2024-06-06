package com.ubaya.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubaya.todoapp.R
import com.ubaya.todoapp.databinding.FragmentCreateTodoBinding
import com.ubaya.todoapp.databinding.FragmentEditTodoBinding
import com.ubaya.todoapp.model.ToDo
import com.ubaya.todoapp.viewmodel.DetailTodoViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [EditTodoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditTodoFragment : Fragment(), RadioClickListener, TodoEditClickListener {
    private lateinit var binding: FragmentEditTodoBinding
    private lateinit var viewModel: DetailTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.radioListener = this
        binding.saveListener = this

        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        binding.txtNewTodo.text = "Edit ToDo"

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

//        binding.buttonAdd.setOnClickListener {
//            todo.title = binding.txtTitle.text.toString()
//            todo.note = binding.txtNote.text.toString()
//            val radio = view.findViewById<RadioButton>(binding.radioGroupPriority.checkedRadioButtonId)
//            todo.priority = radio.tag.toString().toInt()
//            viewModel.update(todo)
//            Toast.makeText(context, "Todo Updated", Toast.LENGTH_SHORT).show()
//        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            binding.todo = it


//            todo = it
//            binding.txtTitle.setText(it.title)
//            binding.txtNote.setText(it.note)
//            when(it.priority){
//                3 -> binding.radioHigh.isChecked == true
//                2 -> binding.radioMedium.isChecked == true
//                1 -> binding.radioLow.isChecked == true
//            }
        })
    }

    override fun onRadioClick(view: View){
        binding.todo!!.priority = view.tag.toString().toInt()
    }

    override fun onTodoSaveChangesClick(view: View) {
        viewModel.update(binding.todo!!)
        Toast.makeText(context, "Todo Updated", Toast.LENGTH_SHORT).show()
    }

}