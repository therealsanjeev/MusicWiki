package com.therealsanjeev.musicwiki.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.therealsanjeev.musicwiki.R
import com.therealsanjeev.musicwiki.adpter.artistAdapter
import com.therealsanjeev.musicwiki.model.recycleview.artist
import com.therealsanjeev.musicwiki.views.ApiViewModel
import kotlinx.android.synthetic.main.fragment_artist.view.*

class ArtistFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: artistAdapter
    private lateinit var tagViewModel: ApiViewModel
    private var responseList= ArrayList<artist>()

    private lateinit var artistTag:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_artist, container, false)

        //recyclerView
        recyclerView=view.recycle_view_artists
        recyclerAdapter= artistAdapter(requireActivity(),responseList)
        recyclerView.layoutManager= GridLayoutManager(requireActivity(), 2)
        recyclerView.adapter=recyclerAdapter

        //getting tag name by MVVM
        tagViewModel= ViewModelProvider(requireActivity()).get(ApiViewModel::class.java)
        tagViewModel.tagInfoResponse.observe(
            requireActivity(), Observer {
                    response ->
                if(response.isSuccessful) {

                    artistTag = response.body()!!.tag.name
                    tagViewModel.getArtistsVM(artistTag)
                    tagViewModel.artistsResponse.observe(
                        requireActivity(), Observer {
                            if (it.isSuccessful) {
                                for (element in it.body()!!.results.artistmatches.artist) {
                                    val item =
                                        artist(element.name, element.image[4].text)
                                    responseList.add(item)
                                }
                                recyclerAdapter.notifyDataSetChanged()

                            }else {
                                Toast.makeText(getActivity(), "Make Sure Internet is Connected!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    )


                }else {
                    Toast.makeText(getActivity(), "Make Sure Internet is Connected!", Toast.LENGTH_SHORT).show()
                }
            }
        )
        return view
    }

}