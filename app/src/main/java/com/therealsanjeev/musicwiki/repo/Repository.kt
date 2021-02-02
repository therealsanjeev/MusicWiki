package com.therealsanjeev.musicwiki.repo

import com.therealsanjeev.musicwiki.network.api.RetrofitInstance

class Repository {
    suspend fun getAllDataRepo(method:String,api_key: String) = RetrofitInstance.api.getData(method,api_key)

    suspend fun getTagInfoRepo(tag:String,api_key: String)=RetrofitInstance.api.getTagInfo(tag,api_key)
}