package com.example.doghelper

import android.content.Context

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.doghelper.constance.Constance

import com.example.doghelper.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yandex.mapkit.MapKitFactory

class HomeActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        val sharePreference = getSharedPreferences("LOGIN_PRE", Context.MODE_PRIVATE)
        val email = sharePreference.getString("EMAIL", "").toString()
        val password = sharePreference.getString("PASSWORD", "").toString()

        val navView: BottomNavigationView = bindingClass.navView
        val navController = findNavController(R.id.nav_host_fragment)
        navView.itemIconTintList = null
        navView.setupWithNavController(navController)


//        bindingClass.bExit.setOnClickListener{
//            val sharePreference = getSharedPreferences("LOGIN_PRE", Context.MODE_PRIVATE)
//            val editor = sharePreference.edit()
//            editor.putString("EMAIL", "")
//            editor.putString("PASSWORD", "")
//            editor.apply()
//            val intent = Intent(this, MainActivity::class.java)
//            intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
//            startActivity(intent)
//        }
    }
}