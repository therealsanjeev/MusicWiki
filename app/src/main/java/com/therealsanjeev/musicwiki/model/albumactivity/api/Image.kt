package com.therealsanjeev.musicwiki.model.albumactivity.api

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text")
    val text: String,
    val size: String
)