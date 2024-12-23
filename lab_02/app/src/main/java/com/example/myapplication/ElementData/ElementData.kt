package com.example.myapplication.ElementData

data class ElementData(
    val imageFileName: String,        // Имя файла изображения
    val artistName: String,           // Имя исполнителя альбома (пластинки)
    val price: Double,                // Цена за пластинку
    val genre: String,                // Жанр
    val quantityInStock: Int          // Количество товаров в наличии
)
