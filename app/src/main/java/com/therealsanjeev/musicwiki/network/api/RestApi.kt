package com.therealsanjeev.musicwiki.network.api

import com.therealsanjeev.musicwiki.model.album.albums
import com.therealsanjeev.musicwiki.model.artists.artists
import com.therealsanjeev.musicwiki.model.taginfo.tagInfo
import com.therealsanjeev.musicwiki.model.topgenre.Toptags
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("?format=json")
    suspend fun getData(@Query("method" )method:String,@Query("api_key")api_key:String):Response<Toptags>

    @GET("?method=tag.getinfo&format=json")
    suspend fun getTagInfo(@Query("tag")tag:String,@Query("api_key")api_key: String):Response<tagInfo>

    @GET("?method=album.search&format=json")
    suspend fun getAlbums(@Query("album")album:String,@Query("api_key")api_key: String):Response<albums>

    @GET("?method=artist.search&format=json")
    suspend fun getArtists(@Query("artist")album:String,@Query("api_key")api_key: String):Response<artists>


}