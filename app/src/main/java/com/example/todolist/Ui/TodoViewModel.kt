package com.example.todolist.Ui

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.models.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TodoViewModel : ViewModel() {
    var mutableLiveData = MutableLiveData<MutableList<String>>()

    private fun getTodoFromFirebase(): MutableList<String> {
        return getUserLogin().listToDod
    }
    fun getTodoFromMVVM() {
        val item = getTodoFromFirebase()
        mutableLiveData.value = item
    }
    fun AddNewTODO(newItem: String) {
        getUserLogin().listToDod.add(newItem)
        AddToFireBase()
    }
    fun RemoveFromFireBase(Item_removed:Int){
        getUserLogin().listToDod.removeAt(Item_removed)
        AddToFireBase()
    }
    private fun AddToFireBase(){
        val username = getUserLogin().UserName
        val email = getUserLogin().email
        val password = getUserLogin().password
        val UserId = getUserLogin().UserId
        val listTodo = getUserLogin().listToDod

        var database = FirebaseDatabase.getInstance()
        var myRef = database.getReference("Users").child(getUserLogin().UserId)
        val newUser = UserModel(username,email,password,UserId, listTodo)

        myRef.setValue(newUser)
    }
}