package com.example.gg.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gg.activity.ProductDetailsActivity
import com.example.gg.databinding.LayoutCartItemBinding
import com.example.gg.roomdb.AppDatabase
import com.example.gg.roomdb.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CartAdapter(val context: Context,val list: List<ProductModel>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    inner class CartViewHolder(val binding: LayoutCartItemBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = LayoutCartItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return  list.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position:  Int) {

        Glide.with(context).load(list[position].productImage).into(holder.binding.imageView3)

        holder.binding.textView10.text = list[position].productName
        holder.binding.textView11.text = list[position].productSp

        holder.itemView.setOnClickListener {

            val intet = Intent(context,ProductDetailsActivity::class.java)
            intet.putExtra("id",list[position].productID)
            context.startActivity(intet)
        }
        val dao=AppDatabase.getInstatance(context).productDao()
        holder.binding.imageView5.setOnClickListener {

            GlobalScope.launch(Dispatchers.IO) {  }
            dao.deleteProduct(
                ProductModel(
                list[position].productID,
                list[position].productName,
                list[position].productImage,
                list[position].productSp
                        )
            )

        }
    }
}