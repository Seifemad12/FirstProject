package com.example.todolist.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.R
import com.example.todolist.Ui.fragments.fragmentsAdapter
import com.example.todolist.databinding.ActivityLoginBinding
import com.google.android.material.tabs.TabLayoutMediator

class LoginActivity : AppCompatActivity() {
    lateinit var b:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.viewpager.adapter = fragmentsAdapter(this)
        TabLayoutMediator(b.tabLayout,b.viewpager){tab,index->
            tab.text = when(index){
                0->"Sign in"
                1->"Sign up"
                else->"other"
            }
        }.attach()

    }
}