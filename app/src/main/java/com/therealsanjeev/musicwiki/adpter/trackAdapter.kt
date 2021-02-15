package com.therealsanjeev.musicwiki.adpter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.therealsanjeev.musicwiki.R
import com.therealsanjeev.musicwiki.model.recycleview.track
import kotlinx.android.synthetic.main.fragment_album_single_layout.view.*
import kotlinx.android.synthetic.main.fragment_album_single_layout.view.artist_name
import kotlinx.android.synthetic.main.fragment_track_single_layout.view.*

class trackAdapter(private val context: Context,private var tracks:List<track>): RecyclerView.Adapter<trackAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var name=view.name
        var artist=view.artist_name_track
        var image=view.image
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): trackAdapter.MyViewHolder {
        val itemView= LayoutInflater.from(context).inflate(
            R.layout.fragment_track_single_layout,
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: trackAdapter.MyViewHolder, position: Int) {
        val response=tracks[position]
        holder.name.text=response.name
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