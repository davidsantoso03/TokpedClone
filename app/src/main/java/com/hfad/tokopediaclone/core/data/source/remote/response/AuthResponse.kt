package com.hfad.tokopediaclone.core.data.source.remote.response

import com.hfad.tokopediaclone.core.data.source.model.User

data class AuthResponse(
    val code: Int? = null,
    val message: String? = null,
    val data: User? = null
)
