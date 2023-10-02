package com.example.gg.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gg.R
import com.bumptech.glide.Glide
import com.example.gg.adapter.CategoryAdapter
import com.example.gg.adapter.ProductAdapter
import com.example.gg.databinding.FragmentHomeBinding
import com.example.gg.model.AddProductModel
import com.example.gg.model.CategoryModel
import com.google.firebase.firestore.ktx.*
import com.google.firebase.ktx.Firebase
import java.util.ArrayList


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        getCategories()
        getSliderImage()
        getProducts()
        return binding.root
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun getSliderImage() {
        Firebase.firestore.collection("slider").document("item")
            .get().addOnSuccessListener {
                Glide.with(requireContext()).load(it.get("img")).into(binding.sliderimage)
            }
    }

    private fun getProducts() {
        val list = ArrayList<AddProductModel>()
        Firebase.firestore.collection("products")
            .get().addOnSuccessListener {
                list.clear()
                for(doc in it.documents)
                {
                    val data = doc.toObject(AddProductModel::class.java)
                    list.add(data!!)
                }
                binding.categoryRecycler.adapter = ProductAdapter(requireContext(),list)
            }
    }

    private fun getCategories() {
        val list = ArrayList<CategoryModel>()
        Firebase.firestore.collection("category")
            .get().addOnSuccessListener {
                list.clear()
                for(doc in it.documents)
                {
                    val data = doc.toObject(CategoryModel::class.java)
                    list.add(data!!)
                }
                binding.categoryRecycler.adapter = CategoryAdapter(requireContext(),list)
            }
    }


}