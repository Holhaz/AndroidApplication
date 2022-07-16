package com.example.myprofile.utils

import android.util.Patterns

object Validator {
    fun checkMail (email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    fun checkPassword (password: String) = Regex("[0-9a-zA-Z]+").matches(password) && password.length >= 8
}