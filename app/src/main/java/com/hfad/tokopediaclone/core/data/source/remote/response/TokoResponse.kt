package com.hfad.tokopediaclone.core.data.source.remote.response

import com.hfad.tokopediaclone.core.data.source.model.User

data class TokoResponse(
    val id:Int?=null,
    val name: String,
    val kota: String,
    val userId:Int
)
