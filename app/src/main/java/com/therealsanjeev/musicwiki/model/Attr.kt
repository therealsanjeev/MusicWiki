package com.therealsanjeev.musicwiki.model

import com.google.gson.annotations.SerializedName

data class Attr(
        @SerializedName("num_res")
    val num_res: Int,
    val offset: Int,
    val total: Int
)
