package com.therealsanjeev.musicwiki.network.api

import com.google.gson.GsonBuilder
import com.therealsanjeev.musicwiki.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val gson = GsonBuilder()
            .create()

    private val retrofit by lazy {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
    }

    val api: RestApi by lazy {
        retrofit.create(RestApi::class.java)
    }

}