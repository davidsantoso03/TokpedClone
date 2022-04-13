package com.hfad.tokopediaclone.ui.alamat.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.tokopediaclone.core.data.source.model.AlamatToko
import com.hfad.tokopediaclone.core.data.source.model.Category
import com.hfad.tokopediaclone.core.data.source.model.Product
import com.hfad.tokopediaclone.databinding.ItemAlamatListBinding
import com.hfad.tokopediaclone.databinding.ItemHomeCategoryBinding
import com.hfad.tokopediaclone.ui.alamat.EditAlamatToko
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.logs
import com.inyongtisto.myhelper.extension.popUpMenu
import com.inyongtisto.myhelper.extension.toJson
import java.time.Instant
import java.util.ArrayList
@SuppressLint("NotifyDataSetChanged")
class AlamatTokoAdapter(val onDelete: (item:AlamatToko, position: Int)-> Unit): //
    RecyclerView.Adapter<AlamatTokoAdapter.ViewHolder>(){ // High Order Function -> function yang bisa dijadiin  parameter pake lambda di kotlin

    private  var data = ArrayList<AlamatToko>()
    inner class  ViewHolder(private val itemBinding: ItemAlamatListBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(item : AlamatToko, position: Int){
            itemBinding.apply {
                var kecamatan = ""
                if (item.kecamatan != null) kecamatan = ", kec. ${item.kecamatan}"
                tvKota.text  = item.kota
                tvEmail.text = item.email
                tvPhone.text = item.phone
                tvAlamat.text = "${item.alamat}$kecamatan, ${item.kota}, ${item.provinsi}, ${item.kodepos}"
                val context = root.context // biar bisa panggil activity didalam adapter dengan merujuk ke ListTokoActivity sebagai root

                btnMenu.setOnClickListener{
                    val listMenu = listOf("Detail","Hapus")
                    root.context.popUpMenu(btnMenu, listMenu){
                        when(it){
                            "Detail" -> context.intentActivity(
                                EditAlamatToko::class.java
                                ,item.toJson())
                            "Hapus" -> onDelete.invoke(item, adapterPosition)
                        }
                    }

//                    val intent = Intent(context,EditAlamatToko::class.java)
//                    intent.putExtra("extra", item.toJson())
//                    context.startActivity(intent)
                }
            }
        }
    }

    fun removeAt(index:Int){
        data.removeAt(index)
        notifyItemRemoved(index)

    }

    fun addItems(items:List<AlamatToko>)
    {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged() // ngasi tau ke adapter ada perubahan data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemAlamatListBinding.inflate(LayoutInflater.from(parent.context),
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