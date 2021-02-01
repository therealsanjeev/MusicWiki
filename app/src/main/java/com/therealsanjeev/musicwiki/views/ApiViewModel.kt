package com.therealsanjeev.musicwiki.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.therealsanjeev.musicwiki.model.topgenre.Toptags
import com.therealsanjeev.musicwiki.repo.Repository
import com.therealsanjeev.musicwiki.utils.Constants.Companion.API_KEY
import kotlinx.coroutines.launch
import retrofit2.Response

class ApiViewModel(val repository: Repository): ViewModel() {

    var apiResponse: MutableLiveData<Response<Toptags>> = MutableLiveData()

    fun getData_All(method:String){
        viewModelScope.launch {
            val response=repository.getAllData(method,API_KEY)
            apiResponse.value=response
        }
    }
}
