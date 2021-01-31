package com.therealsanjeev.musicwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.therealsanjeev.musicwiki.repo.Repository
import com.therealsanjeev.musicwiki.utils.Constants.Companion.API_KEY
import com.therealsanjeev.musicwiki.utils.Constants.Companion.FORMAT
import com.therealsanjeev.musicwiki.views.ApiViewModel
import com.therealsanjeev.musicwiki.views.ApiViewModelFactory

class MainActivity : AppCompatActivity(){

    private lateinit var viewModel: ApiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo=Repository()
        val viewModelFactory=ApiViewModelFactory(repo)

        viewModel=ViewModelProvider(this,viewModelFactory).get(ApiViewModel::class.java)

        viewModel.getTopTag("tag.getTopTags",API_KEY,FORMAT)

        viewModel.apiResponse.observe(
            this, Observer {
                if (it.isSuccessful) {
                    Log.d("RESPONSE", "Getting the response body: ${it.body()}")
                    Log.d("RESPONSE", "Getting the response tags: ${it.code()}")

                } else {
                    Log.d("RESPONSE", "Getting the response errorbody: ${it.errorBody()}")
                }
            }
        )

    }
}