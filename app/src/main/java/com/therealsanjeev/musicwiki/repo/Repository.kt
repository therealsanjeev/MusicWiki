package com.therealsanjeev.musicwiki.repo

import com.therealsanjeev.musicwiki.model.toptags
import com.therealsanjeev.musicwiki.network.api.RetrofitInstance
import retrofit2.Response

class Repository {
    suspend fun getApiResult(method:String,api_Key: String, format: String): Response<toptags> {
        return RetrofitInstance.api.getTopTags(method, api_Key,format)
    }
}