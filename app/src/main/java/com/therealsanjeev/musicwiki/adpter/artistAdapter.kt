package com.therealsanjeev.musicwiki.adpter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.therealsanjeev.musicwiki.Activities.ArtistActivity
import com.therealsanjeev.musicwiki.R
import com.therealsanjeev.musicwiki.model.recycleview.artist
import kotlinx.android.synthetic.main.fragment_artist_single_layout.view.*

class artistAdapter(private val context: Context,private var artists: List<artist>): RecyclerView.Adapter<artistAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {

        var artist=view.name
        var image=view.image
        var artistCardView=view.artist_card_view
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): artistAdapter.MyViewHolder {
        val itemView= LayoutInflater.from(context).inflate(
            R.layout.fragment_artist_single_layout,
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: artistAdapter.MyViewHolder, position: Int) {
        val response=artists[position]
        holder.artist.text=response.name
        holder.image.load(response.image) {
            crossfade(true)
            placeholder(R.drawable.imagebg)
        }
        holder.artistCardView.setOnClickListener {
            val intent = Intent(context, ArtistActivity::class.java).apply {
                putExtra("tag", response.name)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return artists.size
    }
}