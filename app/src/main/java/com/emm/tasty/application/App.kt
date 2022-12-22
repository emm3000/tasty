package com.emm.tasty.application

import android.app.Application
import com.emm.data.di.dataSourceModule
import com.emm.data.di.networkModule
import com.emm.data.di.repositoryModule
import com.emm.tasty.di.viewModelModule
import com.emm.domain.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(
                listOf(
                    viewModelModule,
                    networkModule,
                    useCaseModule,
                    repositoryModule,
                    dataSourceModule,
                )
            )
        }
    }

}