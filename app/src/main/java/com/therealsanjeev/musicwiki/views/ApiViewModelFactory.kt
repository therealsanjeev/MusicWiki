package com.therealsanjeev.musicwiki.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.therealsanjeev.musicwiki.repo.Repository

class ApiViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ApiViewModel() as T
    }
}