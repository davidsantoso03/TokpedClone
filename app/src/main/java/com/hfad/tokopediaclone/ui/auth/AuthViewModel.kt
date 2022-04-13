package com.hfad.tokopediaclone.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hfad.tokopediaclone.core.data.repository.AppRepository
import com.hfad.tokopediaclone.core.data.source.remote.request.LoginRequest
import com.hfad.tokopediaclone.core.data.source.remote.request.RegisterRequest
import com.hfad.tokopediaclone.core.data.source.remote.request.UpdateProfileRequest
import okhttp3.MultipartBody

class AuthViewModel (val repo:AppRepository) : ViewModel() {

    fun login(data:LoginRequest) = repo.login(data).asLiveData()
    fun register(regis:RegisterRequest) = repo.register(regis).asLiveData()
    fun updateProfile(data:UpdateProfileRequest) = repo.updateProfile(data).asLiveData()
    fun uploadUser(id:Int?=null,fileImage:MultipartBody.Part? = null) = repo.uploadUser(id,fileImage).asLiveData()



}