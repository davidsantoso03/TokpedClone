package com.hfad.tokopediaclone.core.data.source.remote.response

import com.hfad.tokopediaclone.core.data.source.model.User

data class BaseSingleResponse<T>( //pakai generic code
    val code: Int? = null,
    val message: String? = null,
    val data: T? = null, // T ini bisa dirubah rubah sesuai yang kita inginkan jadi tidak ada code yang terulang


)
