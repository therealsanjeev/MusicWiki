package com.therealsanjeev.musicwiki.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.therealsanjeev.musicwiki.model.album.albums
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

    val albumResponse:MutableLiveData<Response<albums>> = MutableLiveData()
    fun getAlbumVM(album:String){
        viewModelScope.launch {
            val response=tagRepo.getAlbumRepo(album, API_KEY)
            albumResponse.value=response
        }

    }

    val artistResponse:MutableLiveData<Response<artists>> = MutableLiveData()
    fun getArtistVM(artist:String){
        viewModelScope.launch {
            val response=tagRepo.getArtistRepo(artist, API_KEY)
            artistResponse.value=response
        }

    }

    val trackResponse:MutableLiveData<Response<tracks>> = MutableLiveData()
    fun getTrackVM(track:String){
        viewModelScope.launch {
            val response=tagRepo.getTrackRepo(track, API_KEY)
            trackResponse.value=response
        }

    }
}
