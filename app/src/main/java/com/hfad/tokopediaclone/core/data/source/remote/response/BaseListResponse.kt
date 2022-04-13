package com.hfad.tokopediaclone.core.data.source.remote.response

import com.hfad.tokopediaclone.core.data.source.model.User

data class BaseListResponse<T>( //pakai generic code
    val code: Int? = null,
    val message: String? = null,
    val data: List<T> = emptyList()


)
