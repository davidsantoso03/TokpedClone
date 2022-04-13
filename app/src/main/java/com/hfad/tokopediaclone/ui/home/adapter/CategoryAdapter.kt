package com.hfad.tokopediaclone.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.tokopediaclone.core.data.source.model.Category
import com.hfad.tokopediaclone.core.data.source.model.Product
import com.hfad.tokopediaclone.databinding.ItemHomeCategoryBinding
import java.util.ArrayList
@SuppressLint("NotifyDataSetChanged")
class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){

    private  var data = ArrayList<Category>()
    inner class  ViewHolder(private val itemBinding: ItemHomeCategoryBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(item : Category, position: Int){
            itemBinding.apply {
                tvCategory.text = item.name
                imageCategory.setImageResource(item.image)
            }
        }
    }


    fun addItems(items:List<Category>)
    {
        data.addAll(items)
        notifyDataSetChanged() // ngasi tau ke adapter ada perubahan data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHomeCategoryBinding.inflate(LayoutInflater.from(parent.context),
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