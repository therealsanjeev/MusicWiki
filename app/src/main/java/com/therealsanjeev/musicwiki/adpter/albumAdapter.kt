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
import kotlinx.android.synthetic.main.fragment_album_single_layout.view.*

class albumAdapter(private val context: Context, private var albums: List<album>): RecyclerView.Adapter<albumAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var albumName=view.album_name
        var artist=view.artist_name
        var image=view.album_backGD
        var albumCardView=view.album_card_view
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): albumAdapter.MyViewHolder {
        val itemView= LayoutInflater.from(context).inflate(
            R.layout.fragment_album_single_layout,
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: albumAdapter.MyViewHolder, position: Int) {
        val response=albums[position]
        holder.albumName.text=response.name
        holder.artist.text=response.artist

        holder.image.load(response.image) {
            crossfade(true)
            placeholder(R.drawable.imagebg)
        }
        holder.albumCardView.setOnClickListener {
            val intent = Intent(context, AlbumActivity::class.java).apply {
                putExtra("album", response.name)
                putExtra("artist", response.artist)
                putExtra("image", response.image)
            }
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return albums.size
    }
}