package com.therealsanjeev.musicwiki.network.api

import com.therealsanjeev.musicwiki.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: RestApi by lazy {
        retrofit.create(RestApi::class.java)
    }
}