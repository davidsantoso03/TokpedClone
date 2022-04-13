package com.hfad.tokopediaclone.ui.updateProfile

import android.app.Activity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.github.drjacky.imagepicker.ImagePicker
import com.hfad.tokopediaclone.core.data.source.remote.network.State
import com.hfad.tokopediaclone.core.data.source.remote.request.UpdateProfileRequest
import com.hfad.tokopediaclone.databinding.ActivityUpdateProfileBinding
import com.hfad.tokopediaclone.ui.auth.AuthViewModel
import com.hfad.tokopediaclone.util.Constants
import com.hfad.tokopediaclone.util.Pref
import com.inyongtisto.myhelper.extension.*
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class UpdateProfileActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModel()
    private var _binding: ActivityUpdateProfileBinding? = null
    private val binding get() = _binding!!

    private var fileImage: File? = null //buat nampung uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.toolbar, "Update Profile")
        mainButton()
        setData()

    }

    private fun setData(){
        val user = Pref.getUser()
        if (user != null){
            binding.apply {
                edtName.setText(user.name)
                edtEmail.setText(user.email)
                edtPhone.setText(user.phone)
                Picasso.get().load(Constants.STORAGE_URL + user.image).into(binding.ivImage)

            }
        }
    }
    fun mainButton() {

        binding.btnSave.setOnClickListener {
            if (fileImage!=null){
                upload() //kalau ada file baru diubah kita upload

            }else{
                update() //kalau gaada file baru kita hanya update
            }


        }
        binding.ivImage.setOnClickListener{
            picImage()
        }
    }

    private fun picImage() {
        ImagePicker.with(this)
            .crop()
            .maxResultSize(1080,1080,true)
            .createIntentFromDialog { launcher.launch(it) }
    }
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            val uri = it.data?.data!!
            fileImage = File(uri.path?:"") // membuat sebuah file dari image uri
            Picasso.get().load(uri).into(binding.ivImage)
            upload()
        }
    }

    private fun update() {
        if (binding.edtName.isEmpty()) return
        if (binding.edtPhone.isEmpty()) return
        if (binding.edtEmail.isEmpty()) return

        val idUser = Pref.getUser()?.id
        val body = UpdateProfileRequest(
            //idUser?:0, jika id null maka id nilai nya diganti 0
            idUser.int(),
            binding.edtName.text.toString(),
            binding.edtPhone.text.toString(),
            binding.edtEmail.text.toString(),

        )
        viewModel.updateProfile(body).observe(this, {
            when (it.state) {
                State.SUCCESS -> {
//                    dismisLoading()
                    showToast("Selamat Datang " + it.data?.name)
                    onBackPressed()
                }

                State.ERROR -> {
//                    dismisLoading()
                    toastError(it.message ?: "Login Gagal Bro!")
                }
                State.LOADING -> {
//                    showLoading()
                }
            }


        })
    }


    private fun upload(){
        val idUser = Pref.getUser()?.id
        val file  = fileImage.toMultipartBody() // convert File ke multipart body, didalam kurung sesuaikan dengan image di api kita
        viewModel.uploadUser(
            idUser,
            file
        ).observe(this, {
            when (it.state) {
                State.SUCCESS -> {
                    dismisLoading()
                    update()

                }

                State.ERROR -> {
                    dismisLoading()
                    toastError(it.message ?: "Login Gagal Bro!")
                }
                State.LOADING -> {
                    showLoading()
                }
            }


        })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}