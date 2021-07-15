package com.azhar.awesomeapp

import android.app.Application
import com.azhar.awesomeapp.core.di.networkModule
import com.azhar.awesomeapp.core.di.repositoryModule
import com.azhar.awesomeapp.di.userCaseModule
import com.azhar.awesomeapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    viewModelModule,
                    networkModule,
                    repositoryModule,
                    userCaseModule
                )
            )
        }
    }
}