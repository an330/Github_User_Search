package com.example.githubusersearch.data.repoImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.githubusersearch.data.remote.GitHubApi
import com.example.githubusersearch.data.remote.User
import com.example.githubusersearch.data.remote.UserDetail
import com.example.githubusersearch.data.paging.UserPagingSource
import com.example.githubusersearch.data.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl@Inject constructor(private val api: GitHubApi) :UserRepository{
    override fun searchUsersPager(query: String): Pager<Int, User> = Pager(PagingConfig(pageSize = 20, enablePlaceholders = true)){
        UserPagingSource(api,query)
    }

    override suspend fun getUserDetail(UserName: String): UserDetail = api.getUser(UserName)
}