package com.example.todolist.Ui.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Display
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.todolist.R
import com.example.todolist.Ui.TodoActivity
import com.example.todolist.databinding.FragmentSignInBinding
import com.example.todolist.databinding.FragmentSignUpBinding
import com.example.todolist.models.UserModel
import com.example.todolist.models.setUserLogin
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SignInFragment : Fragment() {
    lateinit var b: FragmentSignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = FragmentSignInBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b = FragmentSignInBinding.bind(view)

        val sharedPreferences =
            activity?.getSharedPreferences("Sign in", Context.MODE_PRIVATE)
        b.username.setText(sharedPreferences!!.getString("userName", ""))
        b.password.setText(sharedPreferences!!.getString("password", ""))

        var database = FirebaseDatabase.getInstance()
        var myRef = database.getReference("Users")
        var mylist = mutableListOf<UserModel>()
        b.login.setOnClickListener {
            var getData = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val username = b.username.text.toString()
                    val password = b.password.text.toString()
                    var found = false
                    mylist.clear()
                    var x = 0
                    for (i in snapshot.children) {
                        val listTodo = i.getValue(UserModel::class.java)
                        mylist.add(listTodo!!)
                        if (username == mylist[x].UserName && password == mylist[x].password) {
                            setUserLogin(
                                UserModel(
                                    mylist[x].UserName,
                                    mylist[x].email,
                                    mylist[x].password,
                                    mylist[x].UserId,
                                    mylist[x].listToDod
                                )
                            )
                            found = true
                            startActivity(Intent(view.context, TodoActivity::class.java))
                        }
                        x++
                    }
                    if (!found) {
                        Toast.makeText(
                            view.context,
                            "User name or password is incorrect",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
            myRef.addValueEventListener(getData)
            myRef.addListenerForSingleValueEvent(getData)

            if (b.remember!!.isChecked) {
                val sharedPreferences =
                    activity?.getSharedPreferences("Sign in", Context.MODE_PRIVATE)
                val editor = sharedPreferences?.edit()
                editor?.putString("userName", b.username.text.toString())
                editor?.putString("password", b.password.text.toString())
                editor?.apply()
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }
}