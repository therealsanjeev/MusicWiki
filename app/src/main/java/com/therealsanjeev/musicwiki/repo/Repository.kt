package com.therealsanjeev.musicwiki.repo

import com.therealsanjeev.musicwiki.network.api.RetrofitInstance

class Repository {

    suspend fun getAllDataRepo(method:String,api_key: String) = RetrofitInstance.api.getData(method,api_key)

    suspend fun getTagInfoRepo(tag:String,api_key: String)=RetrofitInstance.api.getTagInfo(tag,api_key)

    //Genre Activity :
    suspend fun getAlbumsRepo(album:String, api_key: String)=RetrofitInstance.api.getAlbums(album,api_key)

    suspend fun getArtistsRepo(artist:String, api_key: String)=RetrofitInstance.api.getArtists(artist,api_key)

    suspend fun getTracksRepo(track:String, api_key: String)=RetrofitInstance.api.getTracks(track,api_key)

    //Album Activity:
    suspend fun getAlbumRepo(album: String,artist: String,api_key: String)=RetrofitInstance.api.getAlbum(album,artist,api_key)

    //Artist Activity :
    suspend fun getArtistRepo(artist:String, api_key: String)=RetrofitInstance.api.getArtist(artist,api_key)

    suspend fun getTopTracksRepo(artist:String, api_key: String)=RetrofitInstance.api.getTopTracks(artist,api_key)

    suspend fun getTopAlbumRepo(artist:String, api_key: String)=RetrofitInstance.api.getTopAlbum(artist,api_key)

}