package com.azhar.awesomeapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResImages (
    @field:SerializedName("original")
    val original : String,
    @field:SerializedName("large2x")
    val large2x : String,
    @field:SerializedName("large")
    val large : String,
    @field:SerializedName("medium")
    val medium : String,
    @field:SerializedName("small")
    val small : String,
    @field:SerializedName("portrait")
    val portrait : String,
    @field:SerializedName("landscape")
    val landscape : String,
    @field:SerializedName("tiny")
    val tiny : String
        )