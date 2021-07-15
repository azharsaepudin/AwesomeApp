package com.azhar.awesomeapp.core.util

import com.azhar.awesomeapp.core.data.source.remote.response.PhotoGrapherResponse
import com.azhar.awesomeapp.core.domain.model.DataPhotos
import com.azhar.awesomeapp.core.domain.model.PhotoGrapher

object DataMapper {

    fun mapResponseToDomainPhotoGrapher(photoGrapherResponse: PhotoGrapherResponse) : PhotoGrapher{

        val resultsItem: MutableList<DataPhotos> = mutableListOf()
        photoGrapherResponse.photos?.let { list->
            list.map {
                val dataPhotos = DataPhotos(
                    id = it.id,
                    width = it.width,
                    height = it.height,
                    url = it.url,
                    photographer = it.photographer,
                    photographer_url = it.photographer_url,
                    photographer_id = it.photographer_id,
                    avg_color = it.avg_color,
                    imgSmall = it.src.small,
                    imgLarge = it.src.large,
                    liked = it.liked
                )

                resultsItem.add(dataPhotos)
            }
        }

        return PhotoGrapher(
            page = photoGrapherResponse.page,
            perPage = photoGrapherResponse.perPage,
            photos = resultsItem,
            totalResult = photoGrapherResponse.totalResult,
            nextPage = photoGrapherResponse.nextPage,
        )
    }



}