package com.martynhaigh.galileo.testapp

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy.Builder
import com.martynhaigh.galileo.testapp.data.di.dataModule
import com.martynhaigh.galileo.testapp.data.di.repositoryModule
import com.martynhaigh.galileo.testapp.di.viewModelsModule
import com.martynhaigh.galileo.testapp.domain.di.useCaseModule
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

@InternalCoroutinesApi
class GalileoApplication : Application() {

    override fun onCreate() {

        if (BuildConfig.DEBUG) {
            enableStrictMode()
        }
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(object : Timber.Tree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    // TODO : Implement a prod timber tree
                    return
                }

            })
        }

        loadKoin()
    }

    private fun enableStrictMode() {
        StrictMode.setThreadPolicy(
            Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build()
        )
    }

    private fun loadKoin() {
        startKoin {
            androidLogger()
            androidContext(this@GalileoApplication)
            modules(
                listOf(
                    viewModelsModule,
                    dataModule,
                    repositoryModule,
                    useCaseModule
                )
            )
        }
    }
}
