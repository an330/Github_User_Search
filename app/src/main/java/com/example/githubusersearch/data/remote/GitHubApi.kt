package com.example.githubusersearch.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20
    ): SearchResponse

    @GET("users/{username}")
    suspend fun getUser(@Path("username") userName: String): UserDetail

    @GET("users/{username}/repos")
    suspend fun getUserRepos(
        @Path("username") userName: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 30,
        @Query("sort") sort: String = "updated"
    ): List<Repo>
}
