package com.example.todolist.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.databinding.ActivityTodoBinding
import com.example.todolist.models.getUserLogin

class TodoActivity : AppCompatActivity() {
    private lateinit var binding:ActivityTodoBinding
    lateinit var todoModel:TodoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AddToolbar()
        todoModel = ViewModelProvider(this)[TodoViewModel::class.java]
        todoModel.getTodoFromMVVM()
        todoModel.mutableLiveData.observe(this, Observer {
            val myadapter = TODOAdapter()
            binding.recycle.adapter = myadapter
            binding.recycle.layoutManager = LinearLayoutManager(this)
            AddClickLisenter(myadapter)
        })

    }
    private fun AddClickLisenter(myadapter: TODOAdapter){
        myadapter.setOnclickItem(object :TODOAdapter.onItemClickListner{
            override fun onLongItemClick(position: Int) {
                val builder = AlertDialog.Builder(this@TodoActivity)
                builder.setTitle("Do you want to Make Changes to this Todo?")
                builder.setPositiveButton("Remove") { _, _ ->
                    todoModel.RemoveFromFireBase(position)
                    todoModel.getTodoFromMVVM()
                    Toast.makeText(this@TodoActivity, "Item removed Successfully.", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("Edit") { _, _ ->
                    Edit(position)
                }
                builder.setNeutralButton("Cancel") { _, _ ->
                }
                val dialog = builder.create()
                dialog.show()
            }

        })
    }
    private fun Edit(p:Int){
        val builder = AlertDialog.Builder(this)
        val dailogLayout = layoutInflater.inflate(R.layout.add_layout,null)
        val add = dailogLayout.findViewById<EditText>(R.id.toAdd)
        with(builder){
            setTitle("Enter your todo")
            setPositiveButton("Edit"){_,_ ->
                TodoViewModel().RemoveFromFireBase(p)
                TodoViewModel().AddNewTODO(add.text.toString())
                TodoViewModel().getTodoFromMVVM()
                Toast.makeText(this@TodoActivity, "Item Edited Successfully.", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("Cancel"){_,_ ->
            }
            setView(dailogLayout)
            show()
        }
    }
    private fun AddToolbar(){
        binding.toolbar.title = "Todo List"
        setSupportActionBar(binding.toolbar)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.addNew->{
                val builder = AlertDialog.Builder(this)
                val dailogLayout = layoutInflater.inflate(R.layout.add_layout,null)
                val add = dailogLayout.findViewById<EditText>(R.id.toAdd)

                with(builder){
                    setTitle("Enter your todo")
                    setPositiveButton("Add"){_,_ ->
                        TodoViewModel().AddNewTODO(add.text.toString())
                        TodoViewModel().getTodoFromMVVM()
                    }
                    setNegativeButton("Cancel"){_,_ ->
                    }
                    setView(dailogLayout)
                    show()
                }
            }
        }
        return true
    }
}