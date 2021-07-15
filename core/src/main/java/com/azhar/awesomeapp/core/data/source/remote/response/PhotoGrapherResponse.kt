package com.azhar.awesomeapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PhotoGrapherResponse (

    @field:SerializedName("page")
    val page: Int,
    @field:SerializedName("per_page")
    val perPage: Int,
    @field:SerializedName("photos")
    val photos: List<ItemPhotos>,
    @field:SerializedName("total_results")
    val totalResult: Int,
    @field:SerializedName("next_page")
    val nextPage: String,

    )