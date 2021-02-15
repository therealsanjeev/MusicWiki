package com.therealsanjeev.musicwiki.model.albumactivity.api

import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("@attr")
    val attr: Attr,
    val artist: Artist,
    val duration: String,
    val name: String,
    val streamable: Streamable,
    val url: String
)