package com.therealsanjeev.musicwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.therealsanjeev.musicwiki.adpter.genresAdapter
import com.therealsanjeev.musicwiki.model.recycleview.genres
import com.therealsanjeev.musicwiki.repo.Repository
import com.therealsanjeev.musicwiki.utils.Constants.Companion.API_KEY
import com.therealsanjeev.musicwiki.utils.Constants.Companion.FORMAT
import com.therealsanjeev.musicwiki.views.ApiViewModel
import com.therealsanjeev.musicwiki.views.ApiViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity(){

    private lateinit var viewModel: ApiViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter:genresAdapter
    private var responseList= ArrayList<genres>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //recycleView
        recyclerView=recycle_view
        recyclerAdapter= genresAdapter(responseList)

        val layoutManager=GridLayoutManager(applicationContext,3)
        recyclerView.layoutManager=layoutManager
        recyclerView.adapter=recyclerAdapter

        //viewModel
        val repo=Repository()
        val viewModelFactory=ApiViewModelFactory(repo)
        viewModel=ViewModelProvider(this,viewModelFactory).get(ApiViewModel::class.java)

        val method="tag.getTopTags"
        viewModel.getData_All(method)
        viewModel.apiResponse.observe(
            this, Observer {

                if (it.isSuccessful) {
                    Log.d("RESPONSE", "Getting the response body: ${it.body()}")
                    for (element in it.body()!!.toptags.tag) {
                        Log.d("RESPONSE", "Getting the response body: ${element.name}")
                        val item = genres(element.name)
                        responseList.add(item)
                    }
                    Toast.makeText(this, "Finished!!!", Toast.LENGTH_SHORT).show()

                } else {
                    Log.d("RESPONSE", "Getting the response errorbody: ${it.errorBody()}")
                }
            }
        )

    }
}