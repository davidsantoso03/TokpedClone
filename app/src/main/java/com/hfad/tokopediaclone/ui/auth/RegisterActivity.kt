package com.hfad.tokopediaclone.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hfad.tokopediaclone.ui.navigation.BottomNavigationActivity
import com.hfad.tokopediaclone.core.data.source.remote.network.State
import com.hfad.tokopediaclone.core.data.source.remote.request.RegisterRequest
import com.hfad.tokopediaclone.databinding.ActivityRegisterBinding
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModel()
    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()


    }


    fun setData() {

        binding.btnRegister.setOnClickListener {
            register()

        }
    }

    private fun register() {
        if (binding.inputName.isEmpty()) return
        if (binding.inputPhone.isEmpty()) return
        if (binding.inputEmail.isEmpty()) return
        if (binding.inputPassword.isEmpty()) return

        val body = RegisterRequest(
            binding.inputName.text.toString(),
            binding.inputPhone.text.toString(),
            binding.inputEmail.text.toString(),
            binding.inputPassword.text.toString()
        )
        viewModel.register(body).observe(this, {
            when (it.state) {
                State.SUCCESS -> {
                    dismisLoading()
                    showToast("Selamat Datang " + it.data?.name)
                    pushActivity(BottomNavigationActivity::class.java)
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
}