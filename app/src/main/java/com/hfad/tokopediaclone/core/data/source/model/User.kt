package com.hfad.tokopediaclone.core.data.source.model

data class User(
    val created_at: String?,
    val email: String?,
    val id: Int?,
    val name: String?,
    val image: String?,
    val phone: String?,
    val updated_at: String?,
    val toko : Toko?,
)