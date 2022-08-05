package com.example.todolist.Ui;

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.todolist.R
import com.example.todolist.models.getUserLogin


class TODOAdapter : RecyclerView.Adapter<TODOAdapter.ViewHolder>() {
    private lateinit var mlistner:onItemClickListner
    interface onItemClickListner{
        fun onLongItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_todo, parent, false)
        return ViewHolder(view,mlistner)
    }

    override fun getItemCount(): Int {
        return getUserLogin().listToDod.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.list_item.text = getUserLogin().listToDod[position]
    }

    fun setOnclickItem(listner:onItemClickListner){
        mlistner = listner
    }

    class ViewHolder(itemView: View,var listner:onItemClickListner) : RecyclerView.ViewHolder(itemView) {
        val list_item = itemView.findViewById<TextView>(R.id.todo)
        init {
            itemView.setOnLongClickListener {
                listner.onLongItemClick(adapterPosition)
                true
            }
        }
    }
}