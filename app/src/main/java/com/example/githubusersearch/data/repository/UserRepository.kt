package com.example.githubusersearch.data.repository

import androidx.paging.Pager
import com.example.githubusersearch.data.User
import com.example.githubusersearch.data.UserDetail

interface UserRepository {
    fun searchUsersPager(query:String): Pager<Int, User>
    suspend fun getUserDetail(UserName:String):UserDetail
}