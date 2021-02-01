package com.therealsanjeev.musicwiki.repo

import com.therealsanjeev.musicwiki.network.api.RetrofitInstance

class Repository {
    suspend fun getAllData(method:String,api_key: String) = RetrofitInstance.api.getData(method,api_key)
//    suspend fun getAllGenres1(api_key: String) = RetrofitInstance.api.getTopGenres(api_key)
}