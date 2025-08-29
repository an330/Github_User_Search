package com.example.githubusersearch.data

import com.google.gson.annotations.SerializedName

data class SearchResponse (
    @SerializedName("total_count") val totalCount:Int,
    @SerializedName("items") val item:List<User>
)