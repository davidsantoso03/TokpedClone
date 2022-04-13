package com.hfad.tokopediaclone.ui.navigation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.hfad.tokopediaclone.R
import com.hfad.tokopediaclone.core.data.source.remote.network.State
import com.hfad.tokopediaclone.databinding.ActivityBottomNavigationBinding
import com.hfad.tokopediaclone.ui.auth.LoginActivity
import com.hfad.tokopediaclone.ui.toko.TokoSayaActivity
import com.hfad.tokopediaclone.util.Pref
import com.inyongtisto.myhelper.extension.dismisLoading
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.showLoading
import com.inyongtisto.myhelper.extension.toastError
import org.koin.androidx.viewmodel.ext.android.viewModel

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding
    private val viewModel : NavViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNav()
        getUser()

    }
    private fun getUser(){
        viewModel.getUser(Pref.getUser()?.id ?: 0).observe(this, {})
    }

    private fun setupNav() {
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_bottom_navigation)

        navView.setupWithNavController(navController)
        navView.setOnItemSelectedListener {
            if(it.itemId == R.id.navigation_notifications){
                if (Pref.isLogin){
                    Log.d("TAG","onCreate: sudah login")
                    navController.navigate(it.itemId)
                }else{
                    startActivity(Intent(this,LoginActivity::class.java))
                    Log.d("TAG","belom login pindah ke menu login")

                    return@setOnItemSelectedListener true

                }
            }else{
                navController.navigate(it.itemId)
                Log.d("TAG","onCreate: yang lain" + it.itemId)

            }

            return@setOnItemSelectedListener true
        }
    }
}