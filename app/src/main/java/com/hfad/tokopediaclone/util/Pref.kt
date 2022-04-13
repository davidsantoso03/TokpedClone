package com.hfad.tokopediaclone.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.chibatching.kotpref.KotprefModel
import com.hfad.tokopediaclone.core.data.source.model.User
import com.inyongtisto.myhelper.extension.toJson
import com.inyongtisto.myhelper.extension.toModel

object Pref : KotprefModel() {
    //buat menyimpan data dan validasi login ke dalam aplikasi
    var isLogin by booleanPref(false)
    var user by stringPref()

    fun setUser(data: User?){
        user = data.toJson()
    }
    fun getUser(): User?{
        if (user.isEmpty())return null
        return user.toModel(User::class.java)
    }



}
fun getTokoId() = Pref.getUser()?.toko?.id