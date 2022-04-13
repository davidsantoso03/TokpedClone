package com.hfad.tokopediaclone.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hfad.tokopediaclone.ui.navigation.BottomNavigationActivity
import com.hfad.tokopediaclone.core.data.source.remote.network.State
import com.hfad.tokopediaclone.core.data.source.remote.request.LoginRequest
import com.hfad.tokopediaclone.databinding.ActivityLoginBinding
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModel()
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()


    }


    fun setData() {

        binding.btnLogin.setOnClickListener {
            login()

        }
        binding.tvRegister.setOnClickListener {
            pushActivity(RegisterActivity::class.java)
        }
    }

    private fun login() {
        if (binding.inputEmail.isEmpty()) return
        if (binding.inputPassword.isEmpty()) return

        val body = LoginRequest(
            binding.inputEmail.text.toString(),
            binding.inputPassword.text.toString()
        )
        viewModel.login(body).observe(this, {
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