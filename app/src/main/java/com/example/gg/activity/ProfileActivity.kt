package com.example.gg.activity

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gg.R
import com.example.gg.databinding.ActivityAddressBinding
import com.example.gg.databinding.ActivityProfileBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class profileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var preferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences = this.getSharedPreferences("user", MODE_PRIVATE)

        loadUserInfo()

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