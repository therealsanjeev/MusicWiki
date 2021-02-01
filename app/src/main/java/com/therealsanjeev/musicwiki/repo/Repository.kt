package com.therealsanjeev.musicwiki.repo

import com.therealsanjeev.musicwiki.model.Toptags
import com.therealsanjeev.musicwiki.network.api.RetrofitInstance
import retrofit2.Response

class Repository {
    suspend fun getAllGenres(api_key: String) = RetrofitInstance.api.getTopGenres(api_key)
}