package com.example.gg.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gg.activity.ProductDetailsActivity
import com.example.gg.databinding.Item1CategoryProductLayoutBinding
import com.example.gg.model.AddProductModel

class CategoryProductAdapter(val context: Context, val list:ArrayList<AddProductModel>):RecyclerView.Adapter<CategoryProductAdapter.CategoryProductViewHolder>() {
    inner class CategoryProductViewHolder(val binding : Item1CategoryProductLayoutBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryProductViewHolder {
        val binding = Item1CategoryProductLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return CategoryProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoryProductViewHolder, position: Int) {
        Glide.with(context).load(list[position].productCoverImg).into(holder.binding.imageView4)

        holder.binding.textView5.text = list[position].productName
        holder.binding.textView6.text = list[position].productSp

        holder.itemView.setOnClickListener{
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("id",list[position].productID)
            context.startActivity(intent)
        }
    }
}