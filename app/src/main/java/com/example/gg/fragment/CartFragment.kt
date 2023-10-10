package com.example.gg.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.gg.R
import com.example.gg.activity.AddressActivity
import com.example.gg.activity.CategoryActivity
import com.example.gg.adapter.CartAdapter
import com.example.gg.adapter.CategoryAdapter
import com.example.gg.databinding.ActivityProductDetailsBinding
import com.example.gg.databinding.FragmentCartBinding
import com.example.gg.databinding.FragmentHomeBinding
import com.example.gg.roomdb.AppDatabase
import com.example.gg.roomdb.ProductModel

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var list : ArrayList<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_cart, container, false)
        binding = FragmentCartBinding.inflate(layoutInflater)

        val preference = requireContext().getSharedPreferences("info", AppCompatActivity.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putBoolean("isCart",false)
        editor.apply()

        val dao = AppDatabase.getInstatance(requireContext()).productDao()

        list = ArrayList()

        dao.getAllProduct().observe(requireActivity())
        {
            binding.cartRecycler.adapter = CartAdapter(requireContext(),it)
            list.clear()
            for (data in it){
                list.add(data.productID)
            }
            totalCost(it)
        }
        return binding.root
    }

    private fun totalCost(data: List<ProductModel>?) {
        var total = 0
        for(item in data!!)
        {
            total +=  item.productSp!!.toInt()
        }
        binding.textView11.text = "Total Item In Cart Is ${data.size}"
        binding.textView12.text = "Total Cost : $total"

        binding.checkout.setOnClickListener{
            val intent = Intent(context, AddressActivity::class.java)
            val b = Bundle()
            b.putStringArrayList("productIds",list)
            b.putString("totalCost",total.toString())
            intent.putExtras(b)
            startActivity(intent)
        }
    }
}