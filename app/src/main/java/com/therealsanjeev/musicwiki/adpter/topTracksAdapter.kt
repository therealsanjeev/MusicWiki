package com.therealsanjeev.musicwiki.adpter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.therealsanjeev.musicwiki.Activities.AlbumActivity
import com.therealsanjeev.musicwiki.R
import com.therealsanjeev.musicwiki.model.recycleview.album
import com.therealsanjeev.musicwiki.model.recycleview.track
import kotlinx.android.synthetic.main.fragment_album_single_layout.view.album_backGD
import kotlinx.android.synthetic.main.fragment_album_single_layout.view.album_card_view
import kotlinx.android.synthetic.main.fragment_album_single_layout.view.album_name
import kotlinx.android.synthetic.main.fragment_album_single_layout.view.artist_name

class topTracksAdapter(private val context: Context, private var tracks: List<track>): RecyclerView.Adapter<topTracksAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var albumName=view.album_name
        var artist=view.artist_name
        var image=view.album_backGD
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): topTracksAdapter.MyViewHolder {
        val itemView= LayoutInflater.from(context).inflate(
            R.layout.top_single_item,
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: topTracksAdapter.MyViewHolder, position: Int) {
        val response=tracks[position]
        holder.albumName.text=response.name
        holder.artist.text=response.artist

        holder.image.load(response.image) {
            crossfade(true)
            placeholder(R.drawable.imagebg)
        }


    }

    override fun getItemCount(): Int {
        return tracks.size
    }
}