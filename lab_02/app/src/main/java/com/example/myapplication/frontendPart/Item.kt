package com.example.myapplication.frontendPart

data class Item(
    val id: Int,
    val name: String,
    val description: String,
    val imagePath: String // Заменили imageResId на imagePath
)
