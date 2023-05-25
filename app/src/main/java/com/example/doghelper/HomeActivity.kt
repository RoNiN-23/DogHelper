package com.example.doghelper

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.doghelper.constance.Constance
import com.example.doghelper.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        val tvDisplay = findViewById<TextView>(R.id.tvDisplay)

        val sharePreference = getSharedPreferences("LOGIN_PRE", Context.MODE_PRIVATE)

        val email = sharePreference.getString("EMAIL", "").toString()
        val password = sharePreference.getString("PASSWORD", "").toString()

        tvDisplay.text = "$email $password"

        bindingClass.bExit.setOnClickListener{
            val sharePreference = getSharedPreferences("LOGIN_PRE", Context.MODE_PRIVATE)
            val editor = sharePreference.edit()
            editor.putString("EMAIL", "")
            editor.putString("PASSWORD", "")
            editor.apply()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
            startActivity(intent)
        }
    }
}