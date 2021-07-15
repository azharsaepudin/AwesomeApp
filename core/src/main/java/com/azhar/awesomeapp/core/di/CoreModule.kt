package com.azhar.awesomeapp.core.di

import com.azhar.awesomeapp.core.BuildConfig
import com.azhar.awesomeapp.core.data.source.AwesomeRepository
import com.azhar.awesomeapp.core.data.source.remote.RemoteDataSource
import com.azhar.awesomeapp.core.data.source.remote.network.ApiService
import com.azhar.awesomeapp.core.domain.repository.IAwesomeRepository
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {

        val hostname = BuildConfig.HOST_NAME
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/hwzn8zePeQOG3c96zu4nXO6S4ct6O23bvfW+16/fy64=")
            .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .add(hostname, "sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=")
            .build()

        val httpClient = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClient.addInterceptor(logging)

        }

        httpClient.connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()

    }

    single {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASEURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(get())
            .build()

        retrofit.create(ApiService::class.java)
    }

}

val repositoryModule = module {
    single { RemoteDataSource(get()) }

    single<IAwesomeRepository> {
        AwesomeRepository(get())
    }
}
