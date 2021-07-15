package com.azhar.awesomeapp.core.data.source.remote.network

import com.azhar.awesomeapp.core.BuildConfig
import com.azhar.awesomeapp.core.data.source.remote.response.PhotoGrapherResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers(BuildConfig.API_KEY)
    @GET("curated")
    suspend fun getPhotoGrapher(
        @Query("page") page:Int,
        @Query("per_page") per_page:Int
    ): PhotoGrapherResponse
}