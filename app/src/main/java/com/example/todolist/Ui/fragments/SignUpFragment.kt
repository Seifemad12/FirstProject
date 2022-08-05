package com.example.todolist.Ui.fragments

import android.content.Intent
import android.os.Bundle
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
import com.google.firebase.database.FirebaseDatabase

class SignUpFragment : Fragment() {
    lateinit var b: FragmentSignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b = FragmentSignUpBinding.bind(view)
        b.signup.setOnClickListener {
            var database = FirebaseDatabase.getInstance()
            var myRef = database.getReference("Users")
            var UserId = myRef.push().key
            val userModel = UserModel(
                b.username.text.toString(),
                b.email.text.toString(),
                b.password.text.toString(),
                UserId.toString(),
                arrayListOf()
            )

            myRef.child(UserId.toString()).setValue(userModel).addOnSuccessListener {
                Toast.makeText(view.context, "Sign up Successfully", Toast.LENGTH_SHORT).show()
                setUserLogin(userModel)
                b.email.setText("")
                b.username.setText("")
                b.password.setText("")
                startActivity(Intent(view.context,TodoActivity::class.java))
            }.addOnFailureListener {
                Toast.makeText(view.context, "Sign up Failed", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }


}