package com.hfad.tokopediaclone.ui.alamat

import android.os.Bundle
import androidx.lifecycle.whenStarted
import com.hfad.tokopediaclone.core.data.source.model.AlamatToko
import com.hfad.tokopediaclone.core.data.source.remote.network.State
import com.hfad.tokopediaclone.core.data.source.remote.request.CreateTokoRequest
import com.hfad.tokopediaclone.databinding.ActivityAlamatListBinding
import com.hfad.tokopediaclone.databinding.ActivityBukaTokoBinding
import com.hfad.tokopediaclone.ui.alamat.adapter.AlamatTokoAdapter
import com.hfad.tokopediaclone.ui.base.MyActivity
import com.hfad.tokopediaclone.ui.toko.TokoSayaActivity
import com.hfad.tokopediaclone.util.Pref
import com.hfad.tokopediaclone.util.defaultError
import com.inyongtisto.myhelper.extension.*
import kotlinx.coroutines.flow.observeOn
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListAlamatTokoActivity : MyActivity() {
    private lateinit var binding: ActivityAlamatListBinding
    private val viewModel: AlamatTokoViewModel by viewModel()
    private val adapter = AlamatTokoAdapter { item, position ->
        logs("Name: " + item.kota + " $position")
        confirmDeleteAlamat(item, position)

    }

    override fun onCreate(savedInstanceState: Bundle?)
        super.onCreate(savedInstanceState)
        binding = ActivityAlamatListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "List Alamat")
        setupUI()
        getData()
        setupAdapter()
    }

    private fun setupUI() {
        binding.apply {
            lyToolbar.btnTambah.apply {
                setOnClickListener {
                    intentActivity(TambahAlamatToko::class.java)
                }
                toVisible()
            }
        }
    }

    override fun onResume() {
        getData()
        super.onResume()
    }

    private fun confirmDeleteAlamat(item: AlamatToko, position: Int) {
        showConfirmDialog("Hapus Alamat", "Apakah Anda Yakin menghapus alamat ini?", "Hapus") {
            onDelete(item, position)
        }
    }

    private fun onDelete(item: AlamatToko, position: Int) {
        viewModel.delete(item.id).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    adapter.removeAt(position)
                    toastSuccess("Alamat Berhasil Dihapus")
                }

                State.ERROR -> {
                    showErrorDialog(it.message.defaultError())
                    progress.dismiss()

                }
                State.LOADING -> {
                    progress.show()
                }
            }
        }
        adapter.removeAt(position)
    }


    private fun setupAdapter() {
        binding.rvData.adapter = adapter

    }

    private fun getData() {
        viewModel.get().observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    binding.tvError.toGone()
                    val data = it.data ?: emptyList()

                    adapter.addItems(data)
                    if (data.isEmpty()) {
                        binding.tvError.toVisible()
                    }
                }

                State.ERROR -> {
                    binding.tvError.toVisible()

                }
                State.LOADING -> {

                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}