package com.azhar.awesomeapp.core.domain.usecase

import androidx.paging.PagingData
import com.azhar.awesomeapp.core.domain.model.DataPhotos
import kotlinx.coroutines.flow.Flow

interface AwesomeUseCase {

    fun getDataPhotoGraph(): Flow<PagingData<DataPhotos>>

}