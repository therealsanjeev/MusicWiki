package com.therealsanjeev.musicwiki.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.therealsanjeev.musicwiki.model.toptags
import com.therealsanjeev.musicwiki.repo.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ApiViewModel(val repository: Repository): ViewModel() {

    var apiResponse: MutableLiveData<Response<toptags>> = MutableLiveData()

    fun getTopTag(method:String,api_Key: String, format: String){
        viewModelScope.launch {
            val response=repository.getApiResult(method,api_Key,format)
            apiResponse.value=response
        }
    }
}
