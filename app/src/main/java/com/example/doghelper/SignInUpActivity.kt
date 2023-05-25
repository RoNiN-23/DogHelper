package com.example.doghelper

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.doghelper.constance.Constance
import com.example.doghelper.databinding.ActivitySignInUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInUpActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivitySignInUpBinding
    lateinit var etEmail: EditText
    lateinit var etConfPass: EditText
    private lateinit var etPass: EditText
    private lateinit var bDone: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var tvRedirect: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivitySignInUpBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        tvRedirect = findViewById(R.id.tvRedirect)
        bDone = findViewById(R.id.bDone)
        etEmail = findViewById(R.id.etEmailAddressSU)
        etPass = findViewById(R.id.etPasswordSU)
        etConfPass = findViewById(R.id.etConfirmPasswordSU)


        if (intent.getStringExtra(Constance.SIGN_STATE) == Constance.SIGN_IN_STATE) {
            etConfPass.visibility = View.GONE
            bDone.text = getString(R.string.sign_in)
            tvRedirect.text = getString(R.string.redirect_sign_in)
            auth = FirebaseAuth.getInstance()

        } else if (intent.getStringExtra(Constance.SIGN_STATE) == Constance.SIGN_UP_STATE) {
            etConfPass.visibility = View.VISIBLE
            bDone.text = getString(R.string.sign_up)
            tvRedirect.text = getString(R.string.redirect_sign_up)
            auth = Firebase.auth
        }

        bDone.setOnClickListener {
            if (intent.getStringExtra(Constance.SIGN_STATE) == Constance.SIGN_IN_STATE) {
                login()
            } else if (intent.getStringExtra(Constance.SIGN_STATE) == Constance.SIGN_UP_STATE) {
                signUpUser()
            }
        }

        tvRedirect.setOnClickListener {
            if (intent.getStringExtra(Constance.SIGN_STATE) == Constance.SIGN_IN_STATE) {
                val intent = Intent(this, SignInUpActivity::class.java)
                intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_UP_STATE)
                startActivity(intent)
            } else if (intent.getStringExtra(Constance.SIGN_STATE) == Constance.SIGN_UP_STATE) {
                val intent = Intent(this, SignInUpActivity::class.java)
                intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
                startActivity(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()

    }

    private fun login() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        if (email.isBlank() || pass.isBlank()) {
            Toast.makeText(this, "Поля не заполнены", Toast.LENGTH_SHORT).show()
            return
        } else {
            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val sharePreference = getSharedPreferences("LOGIN_PRE", Context.MODE_PRIVATE)
                    val editor = sharePreference.edit()
                    editor.putString("EMAIL", etEmail.text.toString())
                    editor.putString("PASSWORD", etPass.text.toString())
                    editor.apply()
                    Toast.makeText(this, "Авторизация прошла успешно", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,HomeActivity::class.java)
                    startActivity(intent)
                } else
                    Toast.makeText(
                        this,
                        "Неверный адресс электронной почты или пароль",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
    }

    private fun signUpUser() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        val confPass = etConfPass.text.toString()

        if (email.isBlank() || pass.isBlank() || confPass.isBlank()) {
            Toast.makeText(this, "Поля не заполнены", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confPass) {
            Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT)
                .show()
            return
        }

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SignInUpActivity::class.java)
                intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Попробуйте ещё раз!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}