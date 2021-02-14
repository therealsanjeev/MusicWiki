package com.therealsanjeev.musicwiki.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.therealsanjeev.musicwiki.R
import com.therealsanjeev.musicwiki.views.ApiViewModel


class TrackFragment : Fragment() {


    private lateinit var tagViewModel: ApiViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_track, container, false)

        tagViewModel= ViewModelProvider(requireActivity()).get(ApiViewModel::class.java)
        tagViewModel.tagInfoResponse.observe(
            requireActivity(), Observer {
                    response ->
                if(response.isSuccessful) {
                }
            }
        )

        return view
    }


}