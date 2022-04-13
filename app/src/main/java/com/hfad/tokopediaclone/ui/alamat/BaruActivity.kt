package com.hfad.tokopediaclone.ui.alamat

import android.os.Bundle
import com.hfad.tokopediaclone.core.data.source.remote.network.State
import com.hfad.tokopediaclone.core.data.source.remote.request.CreateTokoRequest
import com.hfad.tokopediaclone.databinding.ActivityBukaTokoBinding
import com.hfad.tokopediaclone.ui.base.MyActivity
import com.hfad.tokopediaclone.util.Pref
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BaruActivity : MyActivity() {
    private lateinit var binding: ActivityBukaTokoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBukaTokoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "")
        mainButton()
    }

    private fun mainButton() {

    }




    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}