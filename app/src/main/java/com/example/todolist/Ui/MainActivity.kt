package com.example.todolist.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import com.example.todolist.R
import com.example.todolist.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var b: ActivityMainBinding
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.progress?.visibility = View.VISIBLE
        handler = Handler()
        handler.postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            b.progress?.visibility = View.GONE
        }, 3000)

    }


}