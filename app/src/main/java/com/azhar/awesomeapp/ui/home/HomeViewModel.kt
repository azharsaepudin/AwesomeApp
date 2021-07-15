package com.azhar.awesomeapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.azhar.awesomeapp.core.domain.model.DataPhotos
import com.azhar.awesomeapp.core.domain.usecase.AwesomeUseCase
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val awesomeUseCase: AwesomeUseCase): ViewModel() {

    fun getPhotoGraph(): Flow<PagingData<DataPhotos>> {

        return awesomeUseCase.getDataPhotoGraph().cachedIn(viewModelScope)
    }
}