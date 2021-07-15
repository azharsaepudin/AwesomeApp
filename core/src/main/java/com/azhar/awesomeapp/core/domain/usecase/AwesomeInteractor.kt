package com.azhar.awesomeapp.core.domain.usecase

import androidx.paging.PagingData
import com.azhar.awesomeapp.core.domain.model.DataPhotos
import com.azhar.awesomeapp.core.domain.repository.IAwesomeRepository
import kotlinx.coroutines.flow.Flow

class AwesomeInteractor(private val awesomeRepository: IAwesomeRepository): AwesomeUseCase {

    override fun getDataPhotoGraph(): Flow<PagingData<DataPhotos>> = awesomeRepository.getDataPhotoGraph()

}