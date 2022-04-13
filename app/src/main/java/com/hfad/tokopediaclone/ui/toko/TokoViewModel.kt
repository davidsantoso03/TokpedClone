package com.hfad.tokopediaclone.ui.toko

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hfad.tokopediaclone.core.data.repository.AppRepository
import com.hfad.tokopediaclone.core.data.source.remote.request.CreateTokoRequest

class TokoViewModel(val repo : AppRepository) : ViewModel() {
    fun createToko(data: CreateTokoRequest) = repo.createToko(data).asLiveData()


}