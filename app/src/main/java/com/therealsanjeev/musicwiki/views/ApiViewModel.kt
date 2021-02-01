package com.therealsanjeev.musicwiki.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.therealsanjeev.musicwiki.model.Toptags
import com.therealsanjeev.musicwiki.repo.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ApiViewModel(val repository: Repository): ViewModel() {

    var apiResponse: MutableLiveData<Response<Toptags>> = MutableLiveData()

    fun getTopTag(api_Key: String){
        viewModelScope.launch {
            val response=repository.getAllGenres(api_Key)
            apiResponse.value=response
        }
    }
}
