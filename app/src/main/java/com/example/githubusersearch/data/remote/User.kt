package com.example.githubusersearch.data.remote

import com.google.gson.annotations.SerializedName

data class User(
    val login:String,
    @SerializedName("avatar_url") val avatarUrl:String
)