package com.example.gg.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gg.activity.CheckoutActivity
import com.example.gg.activity.ProductDetailsActivity
import com.example.gg.databinding.LayoutProductItemBinding
import com.example.gg.fragment.CartFragment
import com.example.gg.model.AddProductModel

class ProductAdapter(val context:Context, val list:ArrayList<AddProductModel>):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding :LayoutProductItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = LayoutProductItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val data = list[position]
        Glide.with(context).load(data.productCoverImg).into(holder.binding.imageView2)
        holder.binding.textView4.text = data.productName
        holder.binding.textView.text = data.productCategory
        holder.binding.textView2.text = data.productMrp

        holder.binding.button.text = data.productSp

        holder.binding.button2.setOnClickListener{
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("id",list[position].productID)
            context.startActivity(intent)

        }
        holder.itemView.setOnClickListener{
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("id",list[position].productID)
            context.startActivity(intent)
        }
    }
}