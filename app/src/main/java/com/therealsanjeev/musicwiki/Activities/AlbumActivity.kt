package com.therealsanjeev.musicwiki.Activities

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.Coil.imageLoader
import coil.imageLoader
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.google.android.material.circularreveal.CircularRevealLinearLayout
import com.therealsanjeev.musicwiki.R
import com.therealsanjeev.musicwiki.adpter.genresAdapter
import com.therealsanjeev.musicwiki.model.recycleview.genres
import com.therealsanjeev.musicwiki.utils.Resource
import com.therealsanjeev.musicwiki.views.ApiViewModel
import kotlinx.android.synthetic.main.activity_album_activity.*
import kotlinx.android.synthetic.main.activity_main.*

class AlbumActivity : AppCompatActivity() {
    private lateinit var albumViewModel: ApiViewModel

    private lateinit var backBtn: ImageButton
    private lateinit var albumName: TextView
    private lateinit var albumArtist: TextView
    private lateinit var wiki_summary: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: genresAdapter
    private var responseList= ArrayList<genres>()
    private lateinit var imageBg: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_activity)


        backBtn=back_btn_album
        albumName=album_name
        albumArtist=artist_name
        recyclerView=recycle_view_album_details
        imageBg=album_activity_bg
        wiki_summary=album_summary

        backBtn.setOnClickListener {
            super.onBackPressed()
        }
        val album = intent.getStringExtra("album")
        val artist = intent.getStringExtra("artist")
        Log.d("TAG", "onCreate: $album,$artist")
        albumName.text=album.toString()
        albumArtist.text=artist.toString()

        recyclerView=recycle_view_album_details
        recyclerAdapter= genresAdapter(this, responseList)
        recyclerView.layoutManager= LinearLayoutManager(applicationContext)
        recyclerView.adapter=recyclerAdapter


//        album_summary.text="Yehh humari pawarriii ho rahi h"

        albumViewModel= ViewModelProvider(this).get(ApiViewModel::class.java)
        albumViewModel.getAlbumVM(album.toString(),artist.toString())
        albumViewModel.albumResponse.observe(this, Observer { response ->
            if (response.isSuccessful){
                val result=response.body()!!.album

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

            }
        })

    }
    private fun removeTags(s: String) : String{

        var a = 0
        for (i in s.indices) {
            if (s[i] == '<') {
                a = i
                break
            }
        }
        return s.substring(0, a)
    }


}