package com.hfad.tokopediaclone.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hfad.tokopediaclone.core.data.repository.AppRepository
import com.hfad.tokopediaclone.core.data.source.remote.request.CreateTokoRequest

class NavViewModel(val repo : AppRepository) : ViewModel() {
    fun getUser(id : Int) = repo.getUser(id).asLiveData()


}