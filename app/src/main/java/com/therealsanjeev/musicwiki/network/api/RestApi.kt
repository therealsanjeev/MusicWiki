package com.therealsanjeev.musicwiki.network.api

import com.therealsanjeev.musicwiki.model.album.albums
import com.therealsanjeev.musicwiki.model.albumactivity.api.albumactivity
import com.therealsanjeev.musicwiki.model.artistActivity.artistActivity
import com.therealsanjeev.musicwiki.model.artistActivity.topalbums.topalbums
import com.therealsanjeev.musicwiki.model.artistActivity.toptracks.toptracks
import com.therealsanjeev.musicwiki.model.artists.artists
import com.therealsanjeev.musicwiki.model.taginfo.tagInfo
import com.therealsanjeev.musicwiki.model.topgenre.Toptags
import com.therealsanjeev.musicwiki.model.tracks.tracks
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("?format=json")
    suspend fun getData(@Query("method" )method:String,@Query("api_key")api_key:String):Response<Toptags>

    @GET("?method=tag.getinfo&format=json")
    suspend fun getTagInfo(@Query("tag")tag:String,@Query("api_key")api_key: String):Response<tagInfo>

    //Genre Activity :
    @GET("?method=album.search&format=json")
    suspend fun getAlbums(@Query("album")album:String,@Query("api_key")api_key: String):Response<albums>

    @GET("?method=artist.search&format=json")
    suspend fun getArtists(@Query("artist")album:String,@Query("api_key")api_key: String):Response<artists>

    @GET("?method=track.search&format=json")
    suspend fun getTracks(@Query("track")album:String,@Query("api_key")api_key: String):Response<tracks>

    //Album Activity:
    @GET("?method=album.getinfo&format=json")
    suspend fun getAlbum(@Query("album")album:String,@Query("artist")artist:String,@Query("api_key")api_key: String):Response<albumactivity>

    //Artist Activity :
    @GET("?method=artist.getinfo&format=json")
    suspend fun getArtist(@Query("artist")album:String,@Query("api_key")api_key: String):Response<artistActivity>

    @GET("?method=artist.gettoptracks&format=json")
    suspend fun getTopTracks(@Query("artist")album:String,@Query("api_key")api_key: String):Response<toptracks>

    @GET("?method=artist.gettopalbums&format=json")
    suspend fun getTopAlbum(@Query("artist")album:String,@Query("api_key")api_key: String):Response<topalbums>





}