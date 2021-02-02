package com.therealsanjeev.musicwiki

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.therealsanjeev.musicwiki.adpter.genresAdapter
import com.therealsanjeev.musicwiki.model.recycleview.genres
import com.therealsanjeev.musicwiki.repo.Repository
import com.therealsanjeev.musicwiki.views.ApiViewModel
import com.therealsanjeev.musicwiki.views.ApiViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){

    private lateinit var viewModel: ApiViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewTop10: RecyclerView
    private lateinit var recyclerAdapter:genresAdapter
    private lateinit var recyclerAdapterTop:genresAdapter
    private var responseList= ArrayList<genres>()
    private  var top10=ArrayList<genres>()

    lateinit var btnExpend: ImageView

    var flag=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnExpend=btn_expend

        //recycleView
        recyclerView=recycle_view
        recyclerAdapter= genresAdapter(responseList)
        recyclerView.layoutManager=GridLayoutManager(applicationContext, 3)
        recyclerView.adapter=recyclerAdapter


        recyclerViewTop10=recycle_view_top
        recyclerAdapterTop= genresAdapter(top10)
        recyclerViewTop10.layoutManager=GridLayoutManager(applicationContext, 3)
        recyclerViewTop10.adapter=recyclerAdapterTop


        //viewModel
        val repo=Repository()
        val viewModelFactory=ApiViewModelFactory(repo)
        viewModel=ViewModelProvider(this, viewModelFactory).get(ApiViewModel::class.java)

        val method="tag.getTopTags"
        viewModel.getData_All(method)
        viewModel.apiResponse.observe(
            this, Observer {

                if (it.isSuccessful) {

//                    Log.d("RESPONSE", "Getting the response body: ${it.body()}")
                    for (element in it.body()!!.toptags.tag) {
//                        Log.d("RESPONSE", "Getting the response body: ${element.name}")
                        val item = genres(element.name)

                        responseList.add(item)
//                        Toast.makeText(this, "${element.name}", Toast.LENGTH_SHORT).show()
                    }

                    var i = 0
                    for (it in responseList) {
                        top10.add(it)
                        i++
                        if (i == 12)
                            break
                    }

                    recyclerAdapter.notifyDataSetChanged()
                    recyclerAdapterTop.notifyDataSetChanged()

                    Toast.makeText(this, "Finished!!!", Toast.LENGTH_SHORT).show()

                } else {
                    Log.d("RESPONSE", "Getting the response errorbody: ${it.errorBody()}")
                }
            }
        )
        val vibe = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        btnExpend.setOnClickListener {
            vibe.vibrate(100);
            if(!flag){
                recyclerView.visibility=View.VISIBLE
                recyclerViewTop10.visibility=View.GONE
                flag=true
                btnExpend.setImageResource(R.drawable.down)
            }else{
                recyclerView.visibility=View.GONE
                recyclerViewTop10.visibility=View.VISIBLE
                btnExpend.setImageResource(R.drawable.start)
                flag=false
            }


        }


    }
}