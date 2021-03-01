package com.example.androiddevchallenge.data.model

data class Cat(
    val id:Int = 0 ,
    val name:String = "",
    val desc:String = "",
    val breed:String = "",
    val imgUrl:String = "",
    val location:String = "",
    val color:String = "",
    val fee:String = "",
    val owner: Owner = Owner()
)
