package com.therealsanjeev.musicwiki.model

import com.google.gson.annotations.SerializedName

data class ToptagsX(
        @SerializedName("@attr")
        val attr: Attr,
        val tag: List<tag>
)
