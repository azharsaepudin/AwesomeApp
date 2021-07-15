package com.azhar.awesomeapp.core.domain.model

data class PhotoGrapher (

    val page: Int,
    val perPage: Int,
    val photos: List<DataPhotos>,
    val totalResult: Int,
    val nextPage: String,
        )