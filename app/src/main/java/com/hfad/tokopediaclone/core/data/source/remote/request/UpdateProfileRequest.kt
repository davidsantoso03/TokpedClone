package com.hfad.tokopediaclone.core.data.source.remote.request

data class UpdateProfileRequest(
    val id:Int,
    val name: String,
    val phone:String,
    val email: String,
)
