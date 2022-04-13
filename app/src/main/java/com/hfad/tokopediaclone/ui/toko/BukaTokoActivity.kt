package com.hfad.tokopediaclone.ui.toko

import android.os.Bundle
import com.hfad.tokopediaclone.core.data.source.remote.network.State
import com.hfad.tokopediaclone.core.data.source.remote.request.CreateTokoRequest
import com.hfad.tokopediaclone.databinding.ActivityBukaTokoBinding
import com.hfad.tokopediaclone.ui.base.MyActivity
import com.hfad.tokopediaclone.util.Pref
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BukaTokoActivity : MyActivity() {
    private lateinit var binding: ActivityBukaTokoBinding
    private val viewModel: TokoViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBukaTokoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "")
        mainButton()
    }

    private fun mainButton() {
        binding.btnBuatToko.setOnClickListener {
            bukaToko()
        }
    }

    private fun bukaToko() {
        val body = CreateTokoRequest(
            userId = Pref.getUser()?.id ?: 0,
            name = binding.edtNamaToko.getString(),
            kota = binding.edtLokasiToko.getString()

        )
        viewModel.createToko(body).observe(this, {
            when (it.state) {
                State.SUCCESS -> {
                    dismisLoading()
                    intentActivity(TokoSayaActivity::class.java)

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