package com.hfad.tokopediaclone.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hfad.tokopediaclone.databinding.FragmentHomeBinding
import com.hfad.tokopediaclone.ui.home.adapter.CategoryAdapter
import com.hfad.tokopediaclone.ui.home.adapter.ProductTerbaruAdapter
import com.hfad.tokopediaclone.ui.home.adapter.ProductTerlarisAdapter
import com.hfad.tokopediaclone.ui.home.adapter.SliderAdapter

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapterCategory  =  CategoryAdapter()
    private val sliderCategory  = SliderAdapter()
    private val  productTerlarisAdapter = ProductTerlarisAdapter()
    private val  productTerbaruAdapter = ProductTerbaruAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupAdapter()
        setData()
        button()
        return root


    }
    private fun setupAdapter(){
        binding.rvCategory.adapter = adapterCategory
        binding.rvSlider.adapter  = sliderCategory
        binding.rvProdukTerbaru.adapter  = productTerbaruAdapter
        binding.rvProdukTerlaris.adapter  = productTerlarisAdapter

    }

    private fun setData(){
        viewModel.listCategory.observe(requireActivity(),{
            adapterCategory.addItems(it)
        })
        viewModel.listSlider.observe(requireActivity(),{
            sliderCategory.addItems(it)
        })
        viewModel.listProduct.observe(requireActivity(),{
            productTerlarisAdapter.addItems(it)
            productTerbaruAdapter.addItems(it)
        })
    }
    fun button(){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}