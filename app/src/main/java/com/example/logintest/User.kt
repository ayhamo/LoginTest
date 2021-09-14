package com.example.logintest

data class User(

    var image: String,

    var username: String,

    var email: String,


    ) {

    override fun toString(): String {
        return "User(username='$username', image='$image', username='$email')"
    }


}