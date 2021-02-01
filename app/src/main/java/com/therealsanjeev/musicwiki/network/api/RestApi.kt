package com.therealsanjeev.musicwiki.network.api

import com.therealsanjeev.musicwiki.model.topgenre.Toptags
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("?format=json")
    suspend fun getData(@Query("method" )method:String,@Query("api_key")api_key:String):Response<Toptags>

    @GET("?method=tag.getTopTags&format=json")
    suspend fun getTopGenres11(@Query("api_key")api_key:String):Response<Toptags>
}