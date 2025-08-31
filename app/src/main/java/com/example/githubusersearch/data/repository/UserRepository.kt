package com.example.githubusersearch.data.repository

import androidx.paging.Pager
import com.example.githubusersearch.data.remote.Repo
import com.example.githubusersearch.data.remote.User
import com.example.githubusersearch.data.remote.UserDetail

interface UserRepository {
    fun searchUsersPager(query:String): Pager<Int, User>
    suspend fun getUserDetail(UserName:String): UserDetail
    suspend fun getUserRepos(
        userName: String,
        page: Int,
        perPage: Int = 30,
        sort: String = "updated"
    ): List<Repo>
}