package com.azhar.awesomeapp.core.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.azhar.awesomeapp.core.data.source.remote.RemoteDataSource
import com.azhar.awesomeapp.core.domain.model.DataPhotos
import com.azhar.awesomeapp.core.domain.repository.IAwesomeRepository
import kotlinx.coroutines.flow.Flow

class AwesomeRepository(
    private val remoteDataSource: RemoteDataSource,
) : IAwesomeRepository{


    override fun getDataPhotoGraph(): Flow<PagingData<DataPhotos>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = {remoteDataSource}
        ).flow
    }
}