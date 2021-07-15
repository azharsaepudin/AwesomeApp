package com.azhar.awesomeapp.core.domain.repository

import androidx.paging.PagingData
import com.azhar.awesomeapp.core.domain.model.DataPhotos
import kotlinx.coroutines.flow.Flow

interface IAwesomeRepository {

    fun getDataPhotoGraph(): Flow<PagingData<DataPhotos>>

}