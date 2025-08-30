package com.example.githubusersearch.data.remote

import com.google.gson.annotations.SerializedName

data class UserDetail(
    val login:String,
    val name:String?,
    val bio:String?,
    val followers:Int,
    val following:Int,
    @SerializedName("avatar_url") val avatarUrl:String,
    @SerializedName("public_repos") val publicRepo:Int
)