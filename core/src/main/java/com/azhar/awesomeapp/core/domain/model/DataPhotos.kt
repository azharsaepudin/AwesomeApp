package com.azhar.awesomeapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataPhotos (

    val id : Int,
    val width : Int,
    val height : Int,
    val url : String,
    val photographer : String,
    val photographer_url : String,
    val photographer_id : Int,
    val avg_color : String,
    val imgSmall : String,
    val imgLarge: String,
    val liked : Boolean
        ) : Parcelable