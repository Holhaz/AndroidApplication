package com.example.myprofile.utils

import android.util.Patterns
import java.util.*

fun String.capitalizeWords(): String = split(" ").joinToString(" ")
{ it ->
    it.lowercase(Locale.getDefault()).replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault())
        else it.toString()
    }
}