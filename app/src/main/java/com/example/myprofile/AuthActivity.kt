package com.example.myprofile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myprofile.databinding.ActivityAuthBinding
import com.example.myprofile.utils.Validator
import com.example.myprofile.utils.capitalizeWords

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater).also { setContentView(it.root) }
        preferences = getPreferences(Context.MODE_PRIVATE)

        checkAuth()
        setListener()
    }

    private fun checkAuth() {
        val email = getString("EMAIL_KEY")
        if (email != "")
            onOpenMainActivity(parsingEmailToLogin(email))
    }

    private fun setListener() {
        binding.buttonRegister.setOnClickListener {
            val email = binding.inputEditEmail.text.toString()
            val password = binding.inputEditPassword.text.toString()
            if (Validator.checkMail(email)) {
                if (binding.checkboxRemember.isChecked) {
                    saveString("EMAIL_KEY", email)
                }
                onOpenMainActivity(parsingEmailToLogin(email))
            } else binding.inputEmail.error = getString(R.string.invalid_email_message)

            if (Validator.checkPassword(password)) //TODO
            else binding.inputPassword.error = getString(R.string.invalid_password_message)
        }
    }

    private fun saveString(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    private fun getString(key: String): String {
        return preferences.getString(key, "").toString()
    }

    private fun parsingEmailToLogin(email: String): String {
        val emailWithout = email.substring(0, email.indexOf("@"))
        val array: List<String> = emailWithout.split(".")
        return if (array.size == 2) {
            "${array[0]} ${array[1]}".capitalizeWords()
        } else {
            emailWithout
        }
    }

    private fun onOpenMainActivity(email: String) {
        val intent = Intent(this@AuthActivity, MainActivity::class.java)
        intent.putExtra("EMAIL_KEY", email);
        startActivity(intent)
        finish()
    }
}


