package com.therealsanjeev.musicwiki.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.therealsanjeev.musicwiki.model.taginfo.tagInfo
import com.therealsanjeev.musicwiki.model.topgenre.Toptags
import com.therealsanjeev.musicwiki.repo.Repository
import com.therealsanjeev.musicwiki.utils.Constants.Companion.API_KEY
import kotlinx.coroutines.launch
import retrofit2.Response

class ApiViewModel(val repository: Repository): ViewModel() {

    var apiResponse: MutableLiveData<Response<Toptags>> = MutableLiveData()
    fun getDataAllVM(method:String){
        viewModelScope.launch {
            val response=repository.getAllDataRepo(method,API_KEY)
            apiResponse.value=response
        }
    }

    var tagInfoResponse:MutableLiveData<Response<tagInfo>> = MutableLiveData()
    fun getTagInfoVM(tag:String){
        viewModelScope.launch {
            val response=repository.getTagInfoRepo(tag, API_KEY)
        }
    }
}
