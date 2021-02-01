package com.therealsanjeev.musicwiki.model.tracks

data class Track(
    val artist: String,
    val image: List<Image>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String
)