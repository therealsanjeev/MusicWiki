package com.therealsanjeev.musicwiki.Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.ThreeBounce
import com.therealsanjeev.musicwiki.R
import com.therealsanjeev.musicwiki.adpter.genresAdapter
import com.therealsanjeev.musicwiki.model.recycleview.genres
import com.therealsanjeev.musicwiki.views.ApiViewModel
import kotlinx.android.synthetic.main.activity_album_activity.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_album_activity.spin_kit as spin_kit1

class AlbumActivity : AppCompatActivity() {
    private lateinit var albumViewModel: ApiViewModel

    private lateinit var backBtn: ImageButton
    private lateinit var albumName: TextView
    private lateinit var albumArtist: TextView
    private lateinit var wiki_summary: TextView
    private lateinit var imageBg: ImageView

    //recyclerView:
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: genresAdapter
    private var responseList= ArrayList<genres>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_activity)

        //progressbar :
        val progressBar = spin_kit as ProgressBar
        val doubleBounce: Sprite = ThreeBounce()
        progressBar.indeterminateDrawable = doubleBounce

        setter()

        //getting data from intent:
        val album = intent.getStringExtra("album")
        val artist = intent.getStringExtra("artist")
        Log.d("TAG", "onCreate: $album,$artist")
        albumName.text=album.toString()
        albumArtist.text=artist.toString()

        //accessing ViewModel :
        albumViewModel= ViewModelProvider(this).get(ApiViewModel::class.java)
        albumViewModel.getAlbumVM(album.toString(),artist.toString())
        albumViewModel.albumResponse.observe(this, Observer { response ->
            if (response.isSuccessful){
                val result=response.body()!!.album

                for (element in result.tags.tag) {
                    val item = genres(element.name.toUpperCase())
                    responseList.add(item)
                }
                recyclerAdapter.notifyDataSetChanged()
                Glide.with(this.applicationContext)
                    .load(result.image[4].text)
                    .error(R.drawable.imagebg)
                    .thumbnail(
                        Glide.with(applicationContext)
                            .load(R.drawable.imagebg)
                    )
                    .into(imageBg)

                if (result.wiki!=null){
                    val desc = result.wiki.summary
                    val descWithoutTags = removeTags(desc)
                    wiki_summary.text=descWithoutTags
                }else{
                    wiki_summary.text="No Description Found!!!"
                }

                progressBar.visibility=View.GONE
            }
        })

    }

    private fun setter() {
        backBtn=back_btn_album
        albumName=album_name
        albumArtist=artist_name
        recyclerView=recycle_view_album_details
        imageBg=album_activity_bg
        wiki_summary=album_summary

        //recyclerView :
        recyclerView=recycle_view_album_details
        recyclerAdapter= genresAdapter(this, responseList)
        recyclerView.layoutManager= LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter=recyclerAdapter

        backBtn.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun removeTags(s: String) : String{

        var target = 0
        for (i in s.indices) {
            if (s[i] == '<') {
                target = i
                break
            }
        }
        return s.substring(0, target)
    }


}