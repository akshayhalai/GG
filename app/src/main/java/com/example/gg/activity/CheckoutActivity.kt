package com.example.gg.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.gg.MainActivity
import com.example.gg.R
import com.example.gg.roomdb.AppDatabase
import com.example.gg.roomdb.ProductModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject


class CheckoutActivity : AppCompatActivity(), PaymentResultListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val checkout = Checkout()
        checkout.setKeyID("rzp_test_aHYygkhGeH5TC5");

        val price = intent.getStringExtra("totalCost")

        try {
            val options = JSONObject()
            options.put("name", "GG")
            options.put("description", "Best E-commerce App")
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg")
            options.put("theme.color", "#93DDFF")
            options.put("currency", "INR")
            options.put("amount", (price!!.toInt()*100)) //pass amount in currency subunits
            options.put("prefill.email", "ahalai522@rku.ac.in")
            options.put("prefill.contact", "7284053471")
            val retryObj = JSONObject()
            checkout.open(this, options)
        } catch (e: Exception) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadData() {
        val id = intent.getStringArrayListExtra("productIds")
        for(currentId in id!!)
        {
            fetchData(currentId)
        }
    }


    private fun fetchData(productId: String?) {

        val dao = AppDatabase.getInstatance(this).productDao()

        Firebase.firestore.collection("products")
            .document(productId!!).get().addOnSuccessListener {

                lifecycleScope.launch(Dispatchers.IO) {
                    dao.deleteProduct(ProductModel(productId))
                }


                saveData(it.getString("productName"),
                        it.getString("productSp"), productId)
            }

    }

    private fun saveData(name: String?, price: String?, productId: String) {
        val preferences = this.getSharedPreferences("user", MODE_PRIVATE)
        val data = hashMapOf<String,Any>()
        data["name"] = name!!
        data["price"] = price!!
        data["productId"] = productId
        data["status"] = "Ordered"
        data["userId"] = preferences.getString("number","")!!

        val firestore = Firebase.firestore.collection("allOrders")
        val key = firestore.document().id
        data["orderId"] = key

        firestore.document(key).set( data).addOnSuccessListener {
            Toast.makeText(this, "Order Placed", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
            .addOnFailureListener {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }

    }

    override fun onPaymentSuccess(p0: String?) {
        Toast.makeText(this, "Payment Successfully", Toast.LENGTH_SHORT).show()
        uploadData()

    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this, "Please Select Item", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}