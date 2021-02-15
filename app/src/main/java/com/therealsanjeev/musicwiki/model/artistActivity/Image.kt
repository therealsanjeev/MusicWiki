package com.therealsanjeev.musicwiki.model.artistActivity

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text")
    val text: String,
    val size: String
)