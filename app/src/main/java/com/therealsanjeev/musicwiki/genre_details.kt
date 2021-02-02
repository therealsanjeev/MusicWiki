package com.therealsanjeev.musicwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.therealsanjeev.musicwiki.fragment.AlbumFragment
import com.therealsanjeev.musicwiki.fragment.ArtistFragment
import com.therealsanjeev.musicwiki.fragment.TrackFragment
import com.therealsanjeev.musicwiki.model.album.Album
import com.therealsanjeev.musicwiki.model.topgenre.tag
import com.therealsanjeev.musicwiki.repo.Repository
import com.therealsanjeev.musicwiki.views.ApiViewModel
import com.therealsanjeev.musicwiki.views.ApiViewModelFactory
import kotlinx.android.synthetic.main.activity_genre_details.*

class genre_details : AppCompatActivity() {
    private lateinit var backBtn:ImageButton
    private lateinit var tagName:TextView
    private lateinit var tagSummary:TextView

    private lateinit var tagViewModel: ApiViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre_details)
        setter()

        val tag = intent.getStringExtra("tag")
        tagName.text=tag.toString()

        var tagRepo=Repository()
        val tagViewModelFactory=ApiViewModelFactory(tagRepo)
        tagViewModel=ViewModelProvider(this,tagViewModelFactory).get(ApiViewModel::class.java)

        tagViewModel.getTagInfoVM(tag.toString())
        tagViewModel.tagInfoResponse.observe(
            this, Observer {
                response->
                if (response.isSuccessful){
                    tagSummary.text=response.body()!!.tag.wiki.summary
                }
            }
        )

        //SetUp ViewPager
        setupViewPager(viewpager)
        tabs!!.setupWithViewPager(viewpager)



    }
    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(AlbumFragment(),"Albums")
        adapter.addFragment(ArtistFragment(), "Artists")
        adapter.addFragment(TrackFragment(), "Tracks")
        viewPager.adapter = adapter
    }
    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }
    }

    private fun setter() {
        backBtn=back_btn
        tagName=tag_name
        tagSummary=tag_summary


        backBtn.setOnClickListener {
            super.onBackPressed()
        }
    }
}