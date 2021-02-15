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
import com.therealsanjeev.musicwiki.adpter.trackAdapter
import com.therealsanjeev.musicwiki.model.recycleview.track
import com.therealsanjeev.musicwiki.views.ApiViewModel
import kotlinx.android.synthetic.main.fragment_track.view.*


class TrackFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: trackAdapter
    private lateinit var tagViewModel: ApiViewModel
    private var responseList= ArrayList<track>()

    private lateinit var trackTag:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_track, container, false)



        //recyclerView
        recyclerView=view.recycle_view_tracks
        recyclerAdapter= trackAdapter(requireActivity(),responseList)
        recyclerView.layoutManager= GridLayoutManager(requireActivity(), 2)
        recyclerView.adapter=recyclerAdapter

        tagViewModel= ViewModelProvider(requireActivity()).get(ApiViewModel::class.java)
        tagViewModel.tagInfoResponse.observe(
            requireActivity(), Observer {
                    response ->
                if(response.isSuccessful) {

                    trackTag = response.body()!!.tag.name
                    tagViewModel.getTracksVM(trackTag)
                    tagViewModel.trackResponse.observe(
                        requireActivity(), Observer {
                            if (it.isSuccessful) {
                                for (element in it.body()!!.results.trackmatches.track) {
                                    val item =
                                        track(element.name,element.artist,element.image[3].text)
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