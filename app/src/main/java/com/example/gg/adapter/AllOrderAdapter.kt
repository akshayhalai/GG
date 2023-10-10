package com.example.gg.adapter


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gg.databinding.AllOrderItemLayoutBinding
import com.example.gg.model.AllOrderModel

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AllOrderAdapter(val list: ArrayList<AllOrderModel>, val context: Context)
    :RecyclerView.Adapter<AllOrderAdapter.AllOrderViewHolder>(){
    inner class AllOrderViewHolder(val binding: AllOrderItemLayoutBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllOrderViewHolder {
        return AllOrderViewHolder(
            AllOrderItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AllOrderViewHolder, position: Int) {

        holder.binding.productTitle.text = list[position].name
        holder.binding.productPrice.text = list[position].price


        when(list[position].status)
        {
            "Ordered" -> {
                holder.binding.productStatus.text= "Ordered"
            }

            "Dispatched" -> {
                holder.binding.productStatus.text= "Dispatched"

            }

            "Delivered" -> {

                holder.binding.productStatus.text= "Delivered"
            }
            "Canceled" -> {

                holder.binding.productStatus.text= "Canceled"
            }
        }

    }
}