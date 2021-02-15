package com.therealsanjeev.musicwiki.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.therealsanjeev.musicwiki.model.album.albums
import com.therealsanjeev.musicwiki.model.albumactivity.api.albumactivity
import com.therealsanjeev.musicwiki.model.artists.artists
import com.therealsanjeev.musicwiki.model.taginfo.tagInfo
import com.therealsanjeev.musicwiki.model.topgenre.Toptags
import com.therealsanjeev.musicwiki.model.tracks.tracks
import com.therealsanjeev.musicwiki.repo.Repository
import com.therealsanjeev.musicwiki.utils.Constants.Companion.API_KEY
import kotlinx.coroutines.launch
import retrofit2.Response

class ApiViewModel(): ViewModel() {

    val tagRepo=Repository()
    var apiResponse: MutableLiveData<Response<Toptags>> = MutableLiveData()
    fun getDataAllVM(method:String){
        viewModelScope.launch {
            val response=tagRepo.getAllDataRepo(method,API_KEY)
            apiResponse.value=response
        }
    }

    var tagInfoResponse:MutableLiveData<Response<tagInfo>> = MutableLiveData()
    fun getTagInfoVM(tag:String){
        viewModelScope.launch {

            val response=tagRepo.getTagInfoRepo(tag, API_KEY)
            tagInfoResponse.value=response
        }
    }

    val albumsResponse:MutableLiveData<Response<albums>> = MutableLiveData()
    fun getAlbumsVM(album:String){
        viewModelScope.launch {
            val response=tagRepo.getAlbumsRepo(album, API_KEY)
            albumsResponse.value=response
        }

    }

    val artistResponse:MutableLiveData<Response<artists>> = MutableLiveData()
    fun getArtistsVM(artist:String){
        viewModelScope.launch {
            val response=tagRepo.getArtistsRepo(artist, API_KEY)
            artistResponse.value=response
        }

    }

    val trackResponse:MutableLiveData<Response<tracks>> = MutableLiveData()
    fun getTracksVM(track:String){
        viewModelScope.launch {
            val response=tagRepo.getTracksRepo(track, API_KEY)
            trackResponse.value=response
        }

    }

    //Album Activity:
    val albumResponse:MutableLiveData<Response<albumactivity>> = MutableLiveData()
    fun getAlbumVM(album:String,artist: String){
        viewModelScope.launch {
            val response=tagRepo.getAlbumRepo(album,artist, API_KEY)
            albumResponse.value=response
        }

    }


}
