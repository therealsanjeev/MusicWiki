package com.therealsanjeev.musicwiki.Activities

import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.*
import com.therealsanjeev.musicwiki.R
import com.therealsanjeev.musicwiki.adpter.genresAdapter
import com.therealsanjeev.musicwiki.model.recycleview.genres
import com.therealsanjeev.musicwiki.views.ApiViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){

    private lateinit var viewModel: ApiViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewTop10: RecyclerView
    private lateinit var recyclerAdapter: genresAdapter
    private lateinit var recyclerAdapterTop: genresAdapter
    private var responseList= ArrayList<genres>()
    private  var top10=ArrayList<genres>()

    lateinit var btnExpend: ImageView

    var flag=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //progressBar :
        val progressBar = findViewById<View>(R.id.spin_kit) as ProgressBar
        val doubleBounce: Sprite = ThreeBounce()
        progressBar.indeterminateDrawable = doubleBounce

        setter()

        //accessing ViewModel :
        viewModel= ViewModelProvider(this).get(ApiViewModel::class.java)

        val method="tag.getTopTags"
        viewModel.getDataAllVM(method)
        viewModel.apiResponse.observe(
            this, Observer {

                if (it.isSuccessful) {

                    for (element in it.body()!!.toptags.tag) {
                        val item = genres(element.name.toUpperCase())
                        responseList.add(item)
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
                    progressBar.visibility=View.GONE
                } else {
                    Toast.makeText(this, "Make Sure Internet is Connected!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        )
        val vibe = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            getSystemService(VIBRATOR_SERVICE) as Vibrator
        } else {
            TODO("VERSION.SDK_INT < M")

        }
        btnExpend.setOnClickListener {
            vibe.vibrate(50);
            if(!flag){
                recyclerView.visibility= View.VISIBLE
                recyclerViewTop10.visibility= View.GONE
                flag=true
                btnExpend.setImageResource(R.drawable.down)
            }else{
                recyclerView.visibility= View.GONE
                recyclerViewTop10.visibility= View.VISIBLE
                btnExpend.setImageResource(R.drawable.start)
                flag=false
            }


        }


    }

    private fun setter() {
        btnExpend=btn_expend

        //recycleView
        recyclerView=recycle_view
        recyclerAdapter= genresAdapter(this, responseList)
        recyclerView.layoutManager= GridLayoutManager(applicationContext, 3)
        recyclerView.adapter=recyclerAdapter


        recyclerViewTop10=recycle_view_top
        recyclerAdapterTop= genresAdapter(this, top10)
        recyclerViewTop10.layoutManager= GridLayoutManager(applicationContext, 3)
        recyclerViewTop10.adapter=recyclerAdapterTop

    }

}