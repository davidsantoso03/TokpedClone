package com.hfad.tokopediaclone.core.di

import com.hfad.tokopediaclone.core.data.repository.AppRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AppRepository(get(),get()) }
}