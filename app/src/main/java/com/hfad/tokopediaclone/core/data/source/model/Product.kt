package com.hfad.tokopediaclone.core.data.source.model

data class Product(
    val id: String?,
    val name: String?,
    val price:Int?,
    val delivery:String?,
    val sold:Int?,
    val rating:Double?,
    val discount:Int,
    val grosir:Boolean = false,
    val image:Int

)
