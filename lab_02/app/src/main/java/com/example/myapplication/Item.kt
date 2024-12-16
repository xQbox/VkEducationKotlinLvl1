package com.example.myapplication
import androidx.annotation.DrawableRes

data class Item(
    val id: Int,
    val name: String,
    val description: String,
    val imagePath: String // Заменили imageResId на imagePath
)
