package com.therealsanjeev.musicwiki.network.api

import com.therealsanjeev.musicwiki.model.toptags
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("2.0/")
    suspend fun getTopTags(@Query("method")method:String,@Query("api_key")api_key:String,@Query("format")format:String):Response<toptags>
}