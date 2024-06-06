package com.ubaya.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.todoapp.R
import com.ubaya.todoapp.databinding.FragmentToDoListBinding
import com.ubaya.todoapp.viewmodel.ListTodoViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [ToDoListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ToDoListFragment : Fragment() {
    private lateinit var binding: FragmentToDoListBinding
    private var adapter = TodoListAdapter(arrayListOf(), {todo -> viewModel.checkTask(todo)})
    private lateinit var viewModel: ListTodoViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToDoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListTodoViewModel::class.java)
        viewModel.refresh()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        binding.fabAdd.setOnClickListener{
            val action = ToDoListFragmentDirections.actionToDoListFragment2ToCreateTodoFragment()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()

    }

    fun observeViewModel(){
        viewModel.todoListLD.observe(viewLifecycleOwner, Observer {
            adapter.updateTodoList(it)

            if(it.isEmpty()){
                binding.txtError.text = "Todo is still empty"
                binding.txtError.visibility = View.VISIBLE
            }
        })

        viewModel.todoLoadingLD.observe(viewLifecycleOwner, Observer {
            if(it == false){
                binding.progressBar.visibility = View.GONE
            }
            else{
                binding.progressBar.visibility = View.VISIBLE
            }
        })

        viewModel.todoLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == false){
                binding.txtError.visibility = View.GONE
            }
            else{
                binding.txtError.visibility = View.VISIBLE
                binding.txtError.text = "Unfortunately, we failed to display the data"
            }
        })
    }


}