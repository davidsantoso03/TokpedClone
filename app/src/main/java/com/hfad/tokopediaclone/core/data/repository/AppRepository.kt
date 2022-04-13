package com.hfad.tokopediaclone.core.data.repository

import com.hfad.tokopediaclone.core.data.source.local.LocalDataSource
import com.hfad.tokopediaclone.core.data.source.model.AlamatToko
import com.hfad.tokopediaclone.core.data.source.remote.RemoteDataSource
import com.hfad.tokopediaclone.core.data.source.remote.network.Resource
import com.hfad.tokopediaclone.core.data.source.remote.request.CreateTokoRequest
import com.hfad.tokopediaclone.core.data.source.remote.request.LoginRequest
import com.hfad.tokopediaclone.core.data.source.remote.request.RegisterRequest
import com.hfad.tokopediaclone.core.data.source.remote.request.UpdateProfileRequest
import com.hfad.tokopediaclone.util.Pref
import com.inyongtisto.myhelper.extension.getErrorBody
import com.inyongtisto.myhelper.extension.logs
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody

class AppRepository(val local : LocalDataSource , val remote: RemoteDataSource) {


    fun login (data: LoginRequest)= flow {
        emit(Resource.loading(null))
        try {
            remote.login(data).let {
                if (it.isSuccessful){
                    Pref.isLogin = true
                    val body = it.body()
                    val user  = body?.data
                    Pref.setUser(user)
                    emit(Resource.success(user)) // mengembalikan request body
                    logs("Success" + body.toString())

                }else{

                    emit(Resource.error(it.getErrorBody()?.message
                        ?:"Error Default", null)) // buat ngasi tau error nya apa  contoh salah input password / email
                    logs("Error :" + "keterangan Error")
                    //kenapa ada tanda tanya itu nilainya bisa null , kalau yang gaada ada default valuenya.

                }
            }
        }catch (e:Exception){
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
            logs("Error" + e.message) // tampilin message error
        }
    } // flow buat convert ke live data

    fun register(data: RegisterRequest)= flow {
        emit(Resource.loading(null))
        try {
            remote.register(data).let {
                if (it.isSuccessful){
                    Pref.isLogin = true
                    val body = it.body()
                    val user  = body?.data
                    Pref.setUser(user)
                    emit(Resource.success(body?.data)) // mengembalikan request body
                    logs("Success" + body.toString())

                }else{

                    emit(Resource.error(it.getErrorBody()?.message
                        ?:"Default Error", null)) // buat ngasi tau error nya apa  contoh salah input password / email
                    logs("Error :" + "keterangan Error")
                    //kenapa ada tanda tanya itu nilainya bisa null , kalau yang gaada ada default valuenya.

                }
            }
        }catch (e:Exception){
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
            logs("Error" + e.message) // tampilin message error
        }
    } // flow buat convert ke live data

    fun updateProfile(data: UpdateProfileRequest)= flow {
        emit(Resource.loading(null))
        try {
            remote.updateProfile(data).let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user  = body?.data
                    Pref.setUser(user)
                    emit(Resource.success(body?.data)) // mengembalikan request body

                }else{

                    emit(Resource.error(it.getErrorBody()?.message
                        ?:"Default Error", null)) // buat ngasi tau error nya apa  contoh salah input password / email
                    logs("Error :" + "keterangan Error")
                    //kenapa ada tanda tanya itu nilainya bisa null , kalau yang gaada ada default valuenya.

                }
            }
        }catch (e:Exception){
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    fun uploadUser(id: Int? = null , fileImage : MultipartBody.Part? = null)= flow {
        emit(Resource.loading(null))
        try {
            remote.uploadUser(id, fileImage).let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user  = body?.data
                    Pref.setUser(user)
                    emit(Resource.success(body?.data)) // mengembalikan request body

                }else{

                    emit(Resource.error(it.getErrorBody()?.message
                        ?:"Default Error", null)) // buat ngasi tau error nya apa  contoh salah input password / email
                    logs("Error :" + "keterangan Error")
                    //kenapa ada tanda tanya itu nilainya bisa null , kalau yang gaada ada default valuenya.

                }
            }
        }catch (e:Exception){
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }
    fun createToko (data: CreateTokoRequest)= flow {
        emit(Resource.loading(null))
        try {
            remote.createToko(data).let {
                if (it.isSuccessful){
                    val body = it.body()
                    emit(Resource.success(body?.data)) // mengembalikan request body


                }else{
                    emit(Resource.error(it.getErrorBody()?.message
                        ?:"Error Default", null)) // buat ngasi tau error nya apa  contoh salah input password / email


                }
            }
        }catch (e:Exception){
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
            logs("Error" + e.message) // tampilin message error
        }
    } // flow buat convert ke live data

    fun getUser(id: Int? = null) = flow {
        emit(Resource.loading(null))
        try {
            remote.getUser(id).let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    Pref.setUser(user)
                    emit(Resource.success(user)) // mengembalikan request body

                }else{
                    emit(Resource.error(it.getErrorBody()?.message
                        ?:"Default Error", null)) // buat ngasi tau error nya apa  contoh salah input password / email
                    logs("Error :" + "keterangan Error")
                    //kenapa ada tanda tanya itu nilainya bisa null , kalau yang gaada ada default valuenya.

                }
            }
        }catch (e:Exception){
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }


    fun getAlamatToko() = flow {
        emit(Resource.loading(null))
        try {
            remote.getAlamatToko().let {
                if (it.isSuccessful){
                    val body = it.body()
                    val data = body?.data
                    emit(Resource.success(data)) // mengembalikan request body

                }else{
                    emit(Resource.error(it.getErrorBody()?.message
                        ?:"Default Error", null)) // buat ngasi tau error nya apa  contoh salah input password / email
                    logs("Error :" + "keterangan Error")
                    //kenapa ada tanda tanya itu nilainya bisa null , kalau yang gaada ada default valuenya.

                }
            }
        }catch (e:Exception){
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }


    fun createAlamatToko (data: AlamatToko)= flow {
        emit(Resource.loading(null))
        try {
            remote.createAlamatToko(data).let {
                if (it.isSuccessful){
                    val body = it.body()
                    emit(Resource.success(body?.data)) // mengembalikan request body


                }else{
                    emit(Resource.error(it.getErrorBody()?.message
                        ?:"Error Default", null)) // buat ngasi tau error nya apa  contoh salah input password / email


                }
            }
        }catch (e:Exception){
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
            logs("Error" + e.message) // tampilin message error
        }
    } // flow buat convert ke live data


    fun updateAlamatToko(data: AlamatToko)= flow {
        emit(Resource.loading(null))
        try {
            remote.updateAlamatToko(data).let {
                if (it.isSuccessful){
                    val body = it.body()
                    emit(Resource.success(body?.data)) // mengembalikan request body
                }else{
                    emit(Resource.error(it.getErrorBody()?.message
                        ?:"Error Default", null)) // buat ngasi tau error nya apa  contoh salah input password / email
                }
            }
        }catch (e:Exception){
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
            logs("Error" + e.message) // tampilin message error
        }
    } // flow buat convert ke live data

    fun deleteAlamatToko(id: Int?) = flow {
        emit(Resource.loading(null))
        try {
            remote.deleteAlamatToko(id).let {
                if (it.isSuccessful){
                    val body = it.body()
                    val data = body?.data
                    emit(Resource.success(data)) // mengembalikan request body

                }else{
                    emit(Resource.error(it.getErrorBody()?.message
                        ?:"Default Error", null)) // buat ngasi tau error nya apa  contoh salah input password / email
                    logs("Error :" + "keterangan Error")
                    //kenapa ada tanda tanya itu nilainya bisa null , kalau yang gaada ada default valuenya.

                }
            }
        }catch (e:Exception){
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

}