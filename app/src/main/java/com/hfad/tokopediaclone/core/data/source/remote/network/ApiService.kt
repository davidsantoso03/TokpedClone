package com.hfad.tokopediaclone.core.data.source.remote.network

import com.hfad.tokopediaclone.core.data.source.model.AlamatToko
import com.hfad.tokopediaclone.core.data.source.remote.request.CreateTokoRequest
import com.hfad.tokopediaclone.core.data.source.remote.request.LoginRequest
import com.hfad.tokopediaclone.core.data.source.remote.request.RegisterRequest
import com.hfad.tokopediaclone.core.data.source.remote.request.UpdateProfileRequest
import com.hfad.tokopediaclone.core.data.source.remote.response.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    //sambungan url dari base url + api+ value
    @POST("login")
    suspend fun login( // tambahin suspend biar prosesnya dilakukan di background
        @Body login: LoginRequest
    ): Response<AuthResponse>

    @POST("register")
    suspend fun register( // tambahin suspend biar prosesnya dilakukan di background
        @Body register: RegisterRequest
    ): Response<AuthResponse>

    @PUT("update-user/{id}")
    suspend fun updateProfile( // tambahin suspend biar prosesnya dilakukan di background
        @Path("id") int: Int,
        @Body updateProfile: UpdateProfileRequest
    ): Response<AuthResponse> //response haru sama dengan service yang lain

    @Multipart
    @POST("upload-user/{id}")
    suspend fun uploadUser( // tambahin suspend biar prosesnya dilakukan di background
        @Path("id") int: Int? = null,
        @Part data: MultipartBody.Part? = null
    ): Response<AuthResponse> //response haru sama dengan service yang lain

    @POST("toko")
    suspend fun createToko( // tambahin suspend biar prosesnya dilakukan di background
        @Body data: CreateTokoRequest
    ): Response<BaseSingleResponse<TokoResponse>> //response haru sama dengan service yang lain

    @POST("product")
    suspend fun createProduct( // tambahin suspend biar prosesnya dilakukan di background
        @Body data: CreateTokoRequest
    ): Response<BaseSingleResponse<ProductResponse>> //response haru sama dengan service yang lain

    @GET("toko-user/{id}")
    suspend fun getUser( // tambahin suspend biar prosesnya dilakukan di background
        @Path("id") int: Int? = null
    ): Response<AuthResponse> //response haru sama dengan service yang lain

    @GET("alamat-toko/{id}")
    suspend fun getAlamatToko( // tambahin suspend biar prosesnya dilakukan di background
        @Path("id") idToko : Int? = null
    ): Response<BaseListResponse<AlamatToko >> //response haru sama dengan service yang lain


    @POST("alamat-toko")
    suspend fun createAlamatToko( // tambahin suspend biar prosesnya dilakukan di background
        @Body data: AlamatToko
    ): Response<BaseSingleResponse<AlamatToko>> //response haru sama dengan service yang lain

    @PUT("alamat-toko/{id}")
    suspend fun updateAlamatToko( // tambahin suspend biar prosesnya dilakukan di background
        @Path("id") id: Int? = null,
        @Body data: AlamatToko
    ): Response<BaseSingleResponse<AlamatToko>> //response haru sama dengan service yang lain

    @DELETE("alamat-toko/{id}")
    suspend fun deleteAlamatToko( // tambahin suspend biar prosesnya dilakukan di background
        @Path("id") idToko : Int? = null
    ): Response<BaseSingleResponse <AlamatToko >> //response haru sama dengan service yang lain

}