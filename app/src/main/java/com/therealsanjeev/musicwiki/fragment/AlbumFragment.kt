package com.therealsanjeev.musicwiki.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.therealsanjeev.musicwiki.R
import com.therealsanjeev.musicwiki.adpter.albumAdapter
import com.therealsanjeev.musicwiki.adpter.genresAdapter
import com.therealsanjeev.musicwiki.model.recycleview.album
import com.therealsanjeev.musicwiki.model.recycleview.genres
import com.therealsanjeev.musicwiki.views.ApiViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_album.*
import kotlinx.android.synthetic.main.fragment_album.view.*

class AlbumFragment : Fragment() {

    private lateinit var recyclerView:RecyclerView
    lateinit var recyclerAdapter:albumAdapter
    private lateinit var tagViewModel: ApiViewModel
    private var responseList= ArrayList<album>()

    private lateinit var albumTag:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_album, container, false)

        //recyclerView
        recyclerView=view.recycle_view_albums
        recyclerAdapter= albumAdapter(requireActivity(),responseList)
        recyclerView.layoutManager= GridLayoutManager(requireActivity(), 2)
        recyclerView.adapter=recyclerAdapter

        //getting tag name by MVVM
        tagViewModel= ViewModelProvider(requireActivity()).get(ApiViewModel::class.java)
        tagViewModel.tagInfoResponse.observe(
            requireActivity(), Observer { response ->
                if (response.isSuccessful) {
                    albumTag = response.body()!!.tag.name
                    Log.d("TAG", "onCreateView: $albumTag")
                    tagViewModel.getAlbumVM(albumTag)
                    tagViewModel.albumResponse.observe(
                        requireActivity(), Observer {
                            if (it.isSuccessful) {
                                for (element in it.body()!!.results.albummatches.album) {
                                    val item =
                                        album(element.name, element.artist, element.image[3].text)
                                    Log.d("album", "onCreateView: ${element.name},${element.artist}")
                                    responseList.add(item)
                                }
                                recyclerAdapter.notifyDataSetChanged()

                            }
                        }
                    )

                }
            }
        )


        //adding data


        return view
    }

}