package com.therealsanjeev.musicwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_genre_details.*

class genre_details : AppCompatActivity() {
    lateinit var backBtn:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre_details)
        backBtn=back_btn

        backBtn.setOnClickListener {
            super.onBackPressed()
        }

    }
}