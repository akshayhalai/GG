package com.example.gg.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.load.model.Model
import com.example.gg.activity.Order
import com.example.gg.databinding.FragmentMoreBinding
import com.example.gg.model.UserModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MoreFragment : Fragment() {
    private lateinit var binding: FragmentMoreBinding
    private lateinit var preferences : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMoreBinding.inflate(layoutInflater)
        binding.button6.setOnClickListener{
            startActivity(Intent(context,Order::class.java))
        }

        preferences = requireContext().getSharedPreferences("user", AppCompatActivity.MODE_PRIVATE)
        loadUserInfo()
        return binding.root

    }
    private fun loadUserInfo() {

        Firebase.firestore.collection("users")
            .document(preferences.getString("number","")!!)
            .get().addOnSuccessListener {
                binding.userName.setText(it.getString("userName"))
                binding.userNumber.setText(it.getString("userPhoneNumber"))
                binding.userVillage.setText(it.getString("village"))
                binding.userState.setText(it.getString("state"))
                binding.userCity.setText(it.getString("city"))
                binding.userPincode.setText(it.getString("pinCode"))
            }
    }

}