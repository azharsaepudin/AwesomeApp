package com.azhar.awesomeapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ItemPhotos (

    @field:SerializedName("id")
    val id : Int,
    @field:SerializedName("width")
    val width : Int,
    @field:SerializedName("height")
    val height : Int,
    @field:SerializedName("url")
    val url : String,
    @field:SerializedName("photographer")
    val photographer : String,
    @field:SerializedName("photographer_url")
    val photographer_url : String,
    @field:SerializedName("photographer_id")
    val photographer_id : Int,
    @field:SerializedName("avg_color")
    val avg_color : String,
    @field:SerializedName("src")
    val src : ResImages,
    @field:SerializedName("liked")
    val liked : Boolean
        )