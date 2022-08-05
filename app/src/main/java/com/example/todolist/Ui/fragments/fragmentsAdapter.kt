package com.example.todolist.Ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

class fragmentsAdapter(fragmentManager: FragmentActivity):FragmentStateAdapter(fragmentManager) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->SignInFragment()
            1->SignUpFragment()
            else -> Fragment()
        }
    }


}