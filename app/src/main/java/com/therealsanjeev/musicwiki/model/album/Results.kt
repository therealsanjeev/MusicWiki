package com.therealsanjeev.musicwiki.model.album

import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("@attr")
    val attr: Attr,
    val albummatches: Albummatches,
//    val opensearch:Query: OpensearchQuery,
//    val opensearch:itemsPerPage: String,
//    val opensearch:startIndex: String,
//    val opensearch:totalResults: String
)