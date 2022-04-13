package com.hfad.tokopediaclone.ui.toko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hfad.tokopediaclone.databinding.ActivityBukaTokoBinding
import com.hfad.tokopediaclone.databinding.ActivityTokoSayaBinding
import com.hfad.tokopediaclone.ui.alamat.ListAlamatTokoActivity
import com.hfad.tokopediaclone.util.Constants
import com.hfad.tokopediaclone.util.Pref
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.setToolbar
import com.inyongtisto.myhelper.extension.toGone
import com.squareup.picasso.Picasso

class TokoSayaActivity : AppCompatActivity() {
    private lateinit var binding:ActivityTokoSayaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTokoSayaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar,"")
        setData()
        setup()
    }
    private fun setup(){
    binding.apply {
        btnAlamat.setOnClickListener {
            intentActivity(ListAlamatTokoActivity::class.java)
        }
    }
    }
    private fun setData(){
        val user = Pref.getUser()
        if (user != null){
            binding.apply {
                if (user.toko != null){
                    tvNamaToko.text = user.toko.name
                    tvInisial.text = user.toko.name.getInitial() // ngambil initial nama user
                    Picasso.get().load(Constants.STORAGE_URL + user.toko.name).into(binding.ivProfile)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}