package com.hfad.tokopediaclone.ui.alamat

import android.os.Bundle
import com.hfad.tokopediaclone.core.data.source.model.AlamatToko
import com.hfad.tokopediaclone.core.data.source.remote.network.State
import com.hfad.tokopediaclone.databinding.ActivityTambahAlamatBinding
import com.hfad.tokopediaclone.ui.base.MyActivity
import com.hfad.tokopediaclone.util.defaultError
import com.hfad.tokopediaclone.util.getTokoId
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditAlamatToko : MyActivity() {
    private lateinit var binding: ActivityTambahAlamatBinding
    private val viewModel: AlamatTokoViewModel by viewModel()
    private var provinsiId: Int? = null
    private var kotaId: Int? = null
    private var kecamatanId: Int? = null
    private var provinsi: String? = null
    private var kecamatan: String? = null
    private var kota: String? = null
    private var alamat = AlamatToko()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahAlamatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "Ubah Alamat")
        getExtra()
        setupUi()
        mainButton()
    }


    private fun getExtra() {
        val extra = getStringExtra()
        alamat = extra.toModel(AlamatToko::class.java) ?: AlamatToko()
        binding.apply {
            edtLokasi.setText(alamat.label ?: "Rumah")
            edtAlamat.setText(alamat.alamat)
            edtEmail.setText(alamat.email)
            edtKodepos.setText(alamat.kodepos)
            edtPhone.setText(alamat.phone)
        }
    }

    private fun setupUi() {

        val listProvinsi = listOf("Pilih Provinsi", "Jawa Tengah", "Jawa Timur", "Jawa Barat")
        val listKota = listOf("Pilih Kota", "Lamongan", "Semarang", "Bogor")
        val listKecamatan = listOf("Pilih Kecamatan", "Ngalian", "Parung")
        binding.spnProvinsi.setOnPositionSelectedListener(
            this,
            listProvinsi
        ) {//it = posisi di array nya
            if (it == 0) {
                provinsiId = null
            } else {
                provinsiId = 10
                provinsi = listProvinsi[it]
            }
        }
        binding.spnKecamatan.setOnPositionSelectedListener(this, listKecamatan) {
            if (it == 0) {
                kecamatanId = null
            } else {
                kecamatanId = 5505
                kecamatan = listKecamatan[it]
            }

        }
        binding.spnKota.setOnPositionSelectedListener(this, listKota) {
            if (it == 0) {
                kotaId = null
            } else {
                kotaId = 399
                kota = listKota[it]
            }
        }
        binding.apply {
            val indexProvinsi = listProvinsi.indexOfFirst { it == alamat.provinsi }
            spnProvinsi.setSelection(indexProvinsi)
            val indexKota = listKota.indexOfFirst { it == alamat.kota }
            spnKota.setSelection(indexKota)
            val indexKecamatan = listKecamatan.indexOfFirst { it == alamat.kecamatan }
            spnKecamatan.setSelection(indexKecamatan)
        }

    }

    private fun mainButton() {
        binding.apply {
            lyToolbar.btnSimpan.toVisible()
            lyToolbar.btnSimpan.setOnClickListener {
                if (validate()) simpan() // kalo validasi true semua baru sistem akan simpan data alamat
            }
            lyToolbar.btnSimpan.setOnLongClickListener {
                edtLokasi.setText("Rumah ")
                edtAlamat.setText("jalan Bersamamu")
                edtEmail.setText("iniemail@gmail.com")
                edtKodepos.setText("18920")
                edtPhone.setText("08293818")
                return@setOnLongClickListener true
            }
        }
    }

    private fun validate(): Boolean {
        binding.apply {
            if (edtLokasi.isEmpty()) return false
            if (edtAlamat.isEmpty()) return false
            if (edtKodepos.isEmpty()) return false
            if (edtEmail.isEmpty()) return false
            if (edtPhone.isEmpty()) return false
            if (provinsiId == null) {
                toastSimple("Harap Pilih Provinsi")
                return false
            }
            if (kecamatanId == null) {
                toastSimple("Harap Pilih Kecamatan")
                return false
            }
            if (kotaId == null) {
                toastSimple("Harap Pilih Kota")
                return false
            }

        }
        return true
    }

    private fun simpan() {
        val reqData = AlamatToko(
            id = alamat.id,
            tokoId = getTokoId(),
            label = binding.edtLokasi.getString(),
            email = binding.edtEmail.getString(),
            phone = binding.edtPhone.getString(),
            kodepos = binding.edtKodepos.getString(),
            alamat = binding.edtAlamat.getString(),
            provinsi = provinsi,
            kota = kota,
            kecamatan = kecamatan,
            provinsiId = provinsiId,
            kecamatanId = kecamatanId,
            kotaId = kotaId

        )
        viewModel.update(reqData).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    dismisLoading()
                    toastSuccess("Berhasil Update Alamat")
                    onBackPressed()
                }

                State.ERROR -> {
                    dismisLoading()
                    showErrorDialog(it.message.defaultError())
                }
                State.LOADING -> {
                    showLoading()
                }
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}