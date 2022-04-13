package com.hfad.tokopediaclone.ui.alamat

import android.os.Bundle
import androidx.lifecycle.whenStarted
import com.hfad.tokopediaclone.core.data.source.model.AlamatToko
import com.hfad.tokopediaclone.core.data.source.remote.network.State
import com.hfad.tokopediaclone.core.data.source.remote.request.CreateTokoRequest
import com.hfad.tokopediaclone.databinding.ActivityAlamatListBinding
import com.hfad.tokopediaclone.databinding.ActivityBukaTokoBinding
import com.hfad.tokopediaclone.databinding.ActivityTambahAlamatBinding
import com.hfad.tokopediaclone.ui.alamat.adapter.AlamatTokoAdapter
import com.hfad.tokopediaclone.ui.base.MyActivity
import com.hfad.tokopediaclone.ui.toko.TokoSayaActivity
import com.hfad.tokopediaclone.util.Pref
import com.hfad.tokopediaclone.util.defaultError
import com.hfad.tokopediaclone.util.getTokoId
import com.inyongtisto.myhelper.extension.*
import kotlinx.coroutines.flow.observeOn
import org.koin.androidx.viewmodel.ext.android.viewModel

class TambahAlamatToko : MyActivity() {
    private lateinit var binding: ActivityTambahAlamatBinding
    private val viewModel : AlamatTokoViewModel by viewModel()
    private var provinsiId : Int? = null
    private var kotaId : Int? = null
    private var kecamatanId : Int? = null
    private var provinsi : String? = null
    private var kecamatan : String? = null
    private var kota : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahAlamatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "Tambah Alamat")
        setupUi()
       mainButton()
    }
    private fun setupUi(){

        val listProvinsi = listOf("Pilih Provinsi","Jawa Tengah","Jawa Timur","Jawa Barat")
        val listKota = listOf("Pilih Kota","Lamongan","Semarang","Bogor")
        val listKecamatan = listOf("Pilih Kecamatan","Ngalian","Parung")
        binding.spnProvinsi.setOnPositionSelectedListener(this, listProvinsi){//it = posisi di array nya
                if (it == 0){
                    provinsiId  = null
                }else{
                    provinsiId = 10
                    provinsi = listProvinsi[it]
                }
        }
        binding.spnKecamatan.setOnPositionSelectedListener(this, listKecamatan){
            if (it == 0){
                kecamatanId  = null
            }else{
                kecamatanId = 5505
                kecamatan = listKecamatan[it]
            }

        }
        binding.spnKota.setOnPositionSelectedListener(this, listKota){
            if (it == 0){
                kotaId  = null
            }else{
                kotaId = 399
                kota = listKota[it]
            }
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
    private fun validate(): Boolean{
        binding.apply {
            if (edtLokasi.isEmpty()) return false
            if (edtAlamat.isEmpty()) return false
            if (edtKodepos.isEmpty()) return false
            if (edtEmail.isEmpty()) return false
            if (edtPhone.isEmpty()) return false
            if (provinsiId == null){
                toastSimple("Harap Pilih Provinsi")
                return false
            }
            if (kecamatanId == null){
                toastSimple("Harap Pilih Kecamatan")
                return false
            }
            if (kotaId == null){
                toastSimple("Harap Pilih Kota")
                return false
            }

        }
return true
    }
    private fun simpan() {
        val reqData = AlamatToko(
            tokoId = getTokoId(),
            label = binding.edtLokasi.getString(),
            email = binding.edtEmail.getString(),
            phone= binding.edtPhone.getString(),
            kodepos= binding.edtKodepos.getString(),
            alamat = binding.edtAlamat.getString(),
            provinsi = provinsi,
            kota= kota,
            kecamatan= kecamatan,
            provinsiId= provinsiId,
            kecamatanId = kecamatanId,
            kotaId = kotaId

            )
        viewModel.create(reqData).observe(this){
            when (it.state){
                State.SUCCESS -> {
                    dismisLoading()
                    toastSuccess("Berhasil Menambahkan Alamat")
                    onBackPressed()
                }

                State.ERROR -> {
                    dismisLoading()
                    showErrorDialog(it.message.defaultError() )
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