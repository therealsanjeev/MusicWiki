package com.therealsanjeev.musicwiki.repo

import com.therealsanjeev.musicwiki.network.api.RetrofitInstance

class Repository {
    suspend fun getAllDataRepo(method:String,api_key: String) = RetrofitInstance.api.getData(method,api_key)

    suspend fun getTagInfoRepo(tag:String,api_key: String)=RetrofitInstance.api.getTagInfo(tag,api_key)

    suspend fun getAlbumRepo(album:String,api_key: String)=RetrofitInstance.api.getAlbums(album,api_key)

    suspend fun getArtistRepo(artist:String,api_key: String)=RetrofitInstance.api.getArtists(artist,api_key)

    suspend fun getTrackRepo(track:String,api_key: String)=RetrofitInstance.api.getTracks(track,api_key)

}