package com.hfad.tokopediaclone.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hfad.tokopediaclone.ui.navigation.BottomNavigationActivity
import com.hfad.tokopediaclone.databinding.FragmentAccountBinding
import com.hfad.tokopediaclone.ui.toko.BukaTokoActivity
import com.hfad.tokopediaclone.ui.toko.TokoSayaActivity
import com.hfad.tokopediaclone.ui.updateProfile.UpdateProfileActivity
import com.hfad.tokopediaclone.util.Constants.STORAGE_URL
import com.hfad.tokopediaclone.util.Pref
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.toGone
import com.squareup.picasso.Picasso


class AccountFragment : Fragment() {

    private lateinit var accountViewModel: AccountViewModel
    private var _binding: FragmentAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        accountViewModel =
            ViewModelProvider(this).get(AccountViewModel::class.java)

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setUser()
        buttonClick()
        return root
    }

    override fun onResume() {
        setUser()
        super.onResume()
    }
    fun buttonClick (){
        binding.btnLogout.setOnClickListener {
            Pref.isLogin = false
            intentActivity(BottomNavigationActivity::class.java)
        }
        binding.btnUpdate.setOnClickListener {
            Pref.isLogin = false
            intentActivity(UpdateProfileActivity::class.java)
        }
        binding.btnBukaToko.setOnClickListener{
            intentActivity(BukaTokoActivity::class.java)
        }
    }

    private fun setUser(){
        val user = Pref.getUser()
        if (user != null){
            binding.apply {
                tvName.text = user.name
                tvEmail.text = user.email
                tvPhone.text  = user.phone
                tvInisial.text = user.name.getInitial() // ngambil initial nama user
                Picasso.get().load(STORAGE_URL + user.image).into(binding.ivProfile)

                if (user.toko != null){
                    tvStatusToko.toGone()
                    namaToko.text = user.toko.name
                    binding.btnBukaToko.setOnClickListener {
                        intentActivity(TokoSayaActivity::class.java)
                    }

            }else{
                binding.btnBukaToko.setOnClickListener {
                    intentActivity(BukaTokoActivity::class.java)
                }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}