package com.hfad.tokopediaclone.ui.alamat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hfad.tokopediaclone.core.data.repository.AppRepository
import com.hfad.tokopediaclone.core.data.source.model.AlamatToko
import com.hfad.tokopediaclone.core.data.source.remote.request.CreateTokoRequest

class AlamatTokoViewModel(private val repo : AppRepository) : ViewModel() {
    fun get() = repo.getAlamatToko().asLiveData()
    fun create(data : AlamatToko) = repo.createAlamatToko(data).asLiveData()
    fun update(data : AlamatToko) = repo.updateAlamatToko(data).asLiveData()
    fun delete(id: Int?) = repo.deleteAlamatToko(id).asLiveData()



}