package com.example.githubusersearch.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubusersearch.data.remote.GitHubApi
import com.example.githubusersearch.data.remote.User

class UserPagingSource(
    private val api: GitHubApi,
    private val query:String
): PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? =
       state.anchorPosition?.let {pos->
           state.closestPageToPosition(pos)?.prevKey?.plus(1)
               ?:state.closestPageToPosition(pos)?.nextKey?.minus(1)
       }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key?:1
        return try {
            val response = api.searchUsers(query,page)
            LoadResult.Page(
                data = response.item,
                prevKey = if(page==1) null else page-1,
                nextKey = if(response.item.isEmpty()) null else page +1


            )
        }catch (t:Throwable){
            LoadResult.Error(t)
        }
    }

}