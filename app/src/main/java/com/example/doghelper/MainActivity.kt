package com.example.doghelper

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.doghelper.constance.Constance
import com.example.doghelper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        val sharePreference = getSharedPreferences("LOGIN_PRE", Context.MODE_PRIVATE)
        val getEmail = sharePreference.getString("EMAIL", "")
        val getPassword = sharePreference.getString("PASSWORD", "")
        if (getEmail != "" && getPassword != "") {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        bindingClass.bRegistration.setOnClickListener {
            val intent = Intent(this, SignInUpActivity::class.java)
            intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_UP_STATE)
            startActivity(intent)
        }

        bindingClass.bLogin.setOnClickListener {
            val intent = Intent(this, SignInUpActivity::class.java)
            intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
            startActivity(intent)
        }
    }
}