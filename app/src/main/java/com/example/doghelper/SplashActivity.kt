package com.example.doghelper

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.doghelper.constance.Constance
import com.example.doghelper.databinding.ActivitySplashBinding
import com.yandex.mapkit.MapKitFactory

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        MapKitFactory.setApiKey(Constance.MAPKIT_API_KEY)
        setContentView(binding.root)

        binding.splashView.alpha = 0f
        binding.splashView.animate().setDuration(3000).alpha(1f).withEndAction {
            val sharePreference = getSharedPreferences("LOGIN_PRE", Context.MODE_PRIVATE)
            val getEmail = sharePreference.getString("EMAIL", "")
            val getPassword = sharePreference.getString("PASSWORD", "")
            if (getEmail != "" && getPassword != "") {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, SignInUpActivity::class.java)
                intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
                startActivity(intent)
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

    }
}