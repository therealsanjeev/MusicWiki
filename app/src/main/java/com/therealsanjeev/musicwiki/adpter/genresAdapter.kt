package com.therealsanjeev.musicwiki.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.therealsanjeev.musicwiki.R
import com.therealsanjeev.musicwiki.model.recycleview.genres
import kotlinx.android.synthetic.main.single_item_layout.view.*


class genresAdapter(private var tags:List<genres>): RecyclerView.Adapter<genresAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {

        var tagName=view.tag_name
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.single_item_layout,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val response=tags[position]
        holder.tagName.text=response.tag
    }

    override fun getItemCount(): Int {
        return tags.size
    }

}