package com.example.fromrxjavatocoroutinesflow

import android.app.Application
import com.example.fromrxjavatocoroutinesflow.presentation.di.networkModules
import com.example.fromrxjavatocoroutinesflow.presentation.di.repositoryModules
import com.example.fromrxjavatocoroutinesflow.presentation.di.useCaseModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
//                viewModels +
                repositoryModules +
                        useCaseModules +
                        networkModules
            )
        }
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }
}