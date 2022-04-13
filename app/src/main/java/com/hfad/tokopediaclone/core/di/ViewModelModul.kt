package com.hfad.tokopediaclone.core.di

import com.hfad.tokopediaclone.ui.alamat.AlamatTokoViewModel
import com.hfad.tokopediaclone.ui.auth.AuthViewModel
import com.hfad.tokopediaclone.ui.navigation.NavViewModel
import com.hfad.tokopediaclone.ui.toko.TokoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { TokoViewModel(get()) }
    viewModel { NavViewModel(get()) }
    viewModel { AlamatTokoViewModel(get()) }

}