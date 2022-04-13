package com.hfad.tokopediaclone.core.di

import com.hfad.tokopediaclone.core.data.source.local.LocalDataSource
import com.hfad.tokopediaclone.core.data.source.remote.RemoteDataSource
import com.hfad.tokopediaclone.core.data.source.remote.network.ApiConfig
import org.koin.dsl.module


val appModule = module {
    single { ApiConfig.provideApiService }
    single { RemoteDataSource(get()) }
    single { LocalDataSource() }
}