package com.azhar.awesomeapp.di

import com.azhar.awesomeapp.ui.home.HomeViewModel
import com.azhar.awesomeapp.core.domain.usecase.AwesomeInteractor
import com.azhar.awesomeapp.core.domain.usecase.AwesomeUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userCaseModule = module {
    factory<AwesomeUseCase> { AwesomeInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}