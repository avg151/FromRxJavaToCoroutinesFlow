package com.example.fromrxjavatocoroutinesflow.presentation.di

import com.example.fromrxjavatocoroutinesflow.data.repository.DataRepositoryImpl
import com.example.fromrxjavatocoroutinesflow.data.service.DataService
import com.example.fromrxjavatocoroutinesflow.domain.repository.DataRepository
import com.example.fromrxjavatocoroutinesflow.domain.usecase.GetDataUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val repositoryModules = module {
    single(named(REMOTE_NAME)) { DataRepositoryImpl(get(named(API))) as DataRepository }
}
val useCaseModules = module {
    single {
        GetDataUseCase(get(named(REMOTE_NAME)))
    }
}
val networkModules = module {
    single(named(OKHTTP_INSTANCE)) { createOkHttpClient() }
    single(named(API)) { createWebService<DataService>(get(named(OKHTTP_INSTANCE)), BASE_URL) }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(T::class.java)
}


private const val BASE_URL = "http://universities.hipolabs.com/"
private const val OKHTTP_INSTANCE = "OkHttp"
private const val API = "Api"
private const val REMOTE_NAME = "remote"