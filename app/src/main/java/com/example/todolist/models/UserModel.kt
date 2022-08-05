package com.example.todolist.models

var userModel = UserModel("","","","", arrayListOf())
class UserModel(var UserName:String,var email:String,var password:String,var UserId:String,var listToDod:ArrayList<String>){
    constructor():this("","","","", arrayListOf())
}

fun setUserLogin(user: UserModel){
    userModel = user
}
fun getUserLogin():UserModel{
    return userModel
}