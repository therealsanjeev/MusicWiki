package com.therealsanjeev.musicwiki.network.api

import com.therealsanjeev.musicwiki.model.Toptags
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("?method=tag.getTopTags&format=json")
    suspend fun getTopGenres(@Query("api_key")api_key:String):Response<Toptags>
}