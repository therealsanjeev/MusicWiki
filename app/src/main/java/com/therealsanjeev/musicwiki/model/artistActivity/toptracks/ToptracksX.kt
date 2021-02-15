package com.therealsanjeev.musicwiki.model.artistActivity.toptracks

import com.google.gson.annotations.SerializedName

data class ToptracksX(
    @SerializedName("@attr")
    val attr: Attr,
    val track: List<Track>
)