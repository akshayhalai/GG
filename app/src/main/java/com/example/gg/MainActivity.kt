package com.example.gg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import com.example.gg.activity.LoginActivity
import com.example.gg.databinding.ActivityMainBinding

import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var i = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(FirebaseAuth.getInstance().currentUser == null)
        {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        val navController =  navHostFragment!!.findNavController()

        val popupMenu = PopupMenu(this,null)
        popupMenu.inflate(R.menu.bottom_nav)
        binding.bottomBar.setupWithNavController(popupMenu.menu,navController)

        binding.bottomBar.onItemSelected = {
            when(it){
                0 -> {
                    i =0;
                    navController.navigate(R.id.homeFragment)
                }
                1 -> i = 1
                2 -> i = 2
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        if(i == 0)
        {
            finish()
        }
    }

}
//
//Handler().postDelayed({
//    val i = Intent(this@SplashScreen, MainActivity::class.java)
//    startActivity(i)
//    finish()
//}, SPLASH_TIME_OUT.toLong())
//}
//
//companion object {
//    private const val SPLASH_TIME_OUT = 3000
//}