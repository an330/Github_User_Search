package com.example.githubusersearch.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubusersearch.data.GitHubApi
import com.example.githubusersearch.data.User

class UserPagingSource(
    private val api: GitHubApi,
    private val query:String
): PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        TODO("Not yet implemented")
    }

}