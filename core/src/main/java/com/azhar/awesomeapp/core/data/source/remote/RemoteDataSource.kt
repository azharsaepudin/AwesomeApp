package com.azhar.awesomeapp.core.data.source.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.azhar.awesomeapp.core.data.source.remote.network.ApiService
import com.azhar.awesomeapp.core.domain.model.DataPhotos
import com.azhar.awesomeapp.core.util.DataMapper
import com.azhar.awesomeapp.core.util.EspressoIdlingResource
import java.io.IOException
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService): PagingSource<Int, DataPhotos>() {

    override fun getRefreshKey(state: PagingState<Int, DataPhotos>): Int? {
       return state.anchorPosition?.let { anchorPosition->
           val anchorPage = state.closestPageToPosition(anchorPosition)
           anchorPage?.prevKey?.plus(1)?: anchorPage?.nextKey?.minus(1)
       }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataPhotos> {

        val position = params.key ?: 1
        EspressoIdlingResource.increment()
        return try {
            apiService.getPhotoGrapher(position, params.loadSize).run {
                EspressoIdlingResource.decrement()
                val data = DataMapper.mapResponseToDomainPhotoGrapher(this)

                val mData = data.photos
                val nextKey = if (mData.isEmpty()){
                    null
                }else{
                    position + (params.loadSize / 20)
                }

                LoadResult.Page(
                    data = data.photos,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = nextKey
                )
            }

        } catch (e: Exception) {
            e.message?.let { Log.d("LAIN", it) }
            return LoadResult.Error(e)

        }catch (e: IOException){
            e.message?.let { Log.d("NETWORK", it) }
            return LoadResult.Error(e.also { "Cek koneksi internetmu ya" })
        }
    }
}