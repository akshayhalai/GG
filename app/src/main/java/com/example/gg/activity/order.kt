package com.example.gg.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gg.adapter.AllOrderAdapter
import com.example.gg.databinding.ActivityOrderBinding
import com.example.gg.model.AllOrderModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Order : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding
    private lateinit var list : ArrayList<AllOrderModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOrderBinding.inflate(layoutInflater)
        list = ArrayList()

        val preferences = this.getSharedPreferences("user", MODE_PRIVATE)


        Firebase.firestore.collection("allOrders").whereEqualTo("userId",preferences.getString("number","")!!)
            .get().addOnSuccessListener {
                list.clear()
                for(doc in it)
                {
                    val data = doc.toObject(AllOrderModel::class.java)
                    list.add(data)
                }
                binding.recyclerview.adapter = AllOrderAdapter(list,this)

            }
        setContentView(binding.root)

    }
}