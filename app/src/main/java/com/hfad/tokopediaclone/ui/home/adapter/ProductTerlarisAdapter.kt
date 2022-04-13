package com.hfad.tokopediaclone.ui.home.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.tokopediaclone.core.data.source.model.Category
import com.hfad.tokopediaclone.core.data.source.model.Product
import com.hfad.tokopediaclone.databinding.ItemHomeCategoryBinding
import com.hfad.tokopediaclone.databinding.ItemHomeProdukTerbaruBinding
import com.hfad.tokopediaclone.databinding.ItemHomeProdukTerlarisBinding
import com.inyongtisto.myhelper.extension.coret
import com.inyongtisto.myhelper.extension.toGone
import com.inyongtisto.myhelper.extension.toRupiah
import com.inyongtisto.myhelper.extension.toVisible
import java.util.ArrayList

@SuppressLint("NotifyDataSetChanged")
class ProductTerlarisAdapter : RecyclerView.Adapter<ProductTerlarisAdapter.ViewHolder>(){

    private  var data = ArrayList<Product>()
    inner class  ViewHolder(private val itemBinding: ItemHomeProdukTerlarisBinding) : RecyclerView.ViewHolder(itemBinding.root){
        @SuppressLint("SetTextI18n")
        fun bind(item : Product, position: Int){
            itemBinding.apply {
                val harga = item.price?:0
               ivImage.setImageResource(item.image)
                tvName.text = item.name
                tvHarga.text = item.price.toRupiah()
                tvDelivery.text = item.delivery
                tvRating.text = ""+item.rating + " | terjual "+ item.sold
                if (item.discount != 0 ){
                    lyGrosir.toGone()
                    lyDiskon.toVisible()
                    tvDiskon.text  = "${item.discount}%"
                    tvHarga.text =  (harga - ((item.discount.toDouble() / 100) * harga)).toRupiah() // menghitung diskon
                    tvHargaAsli.text = item.price.toRupiah()
                    tvHargaAsli.coret()
                }

            }
        }
    }


    fun addItems(items:List<Product>)
    {
        data.addAll(items)
        notifyDataSetChanged() // ngasi tau ke adapter ada perubahan data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeProdukTerlarisBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position],position)
    }

    override fun getItemCount(): Int {
        return data.size

    }
}