package com.hfad.tokopediaclone.util

import android.app.Application
import com.chibatching.kotpref.Kotpref
import com.hfad.tokopediaclone.core.di.appModule
import com.hfad.tokopediaclone.core.di.repositoryModule
import com.hfad.tokopediaclone.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Kotpref.init(this)
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(appModule, viewModelModule, repositoryModule))
        }
    }
}