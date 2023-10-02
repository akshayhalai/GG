package com.example.gg.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.gg.MainActivity
import com.example.gg.databinding.ActivityProductDetailsBinding
import com.example.gg.roomdb.AppDatabase
import com.example.gg.roomdb.ProductDao
import com.example.gg.roomdb.ProductModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getProductDetails(intent.getStringExtra("id"))
    }

    private fun getProductDetails(proID: String?) {
        Firebase.firestore.collection("products")
            .document(proID!!).get().addOnSuccessListener {
                val list = it.get("productImages") as ArrayList<String>
                val name = it.getString("productName")
                val productSp = it.getString("productSp")
                val productDesc = it.getString("productDescription")

                binding.textView3.text =name
                binding.textView7.text =productSp
                binding.textView8.text =productDesc


                val slidelist = ArrayList<SlideModel>()
                for(data in list)
                {
                    slidelist.add(SlideModel(data,ScaleTypes.CENTER_CROP))
                }

                cartAction(proID,name,productSp,it.getString("productCoverImg"))
                binding.imageSlider.setImageList(slidelist)

            }.addOnFailureListener {
                Toast.makeText(this, "Something went Wrong..", Toast.LENGTH_SHORT).show()
            }

    }

    @SuppressLint("SetTextI18n")
    private fun cartAction(proID: String, name: String?, productSp: String?, coverImg: String?) {

        val productDao = AppDatabase.getInstatance(this).productDao()
        if(productDao.isExit(proID) != null)
        {
            binding.textView9.text = "Go To Cart"
        }else
        {
            binding.textView9.text = "Add To Cart"
        }

        binding.textView9.setOnClickListener{
            if(productDao.isExit(proID) != null)
            {
                openCart()
            }else
            {
                addToCart(productDao,proID,name,productSp,coverImg)
            }
        }
    }

    private fun addToCart(
        productDao: ProductDao,
        proID: String,
        name: String?,
        productSp: String?,
        coverImg: String?
    ) {

        val data = ProductModel(proID,name,coverImg,productSp)

        lifecycleScope.launch(Dispatchers.IO)
        {
            productDao.insertProduct(data)
            binding.textView9.text = "Go To Cart"
        }
    }

    private fun openCart() {
        val preference = this.getSharedPreferences("info", MODE_PRIVATE)
        val editor = preference.edit()
        editor.putBoolean("isCart",true)
        editor.apply()

        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}