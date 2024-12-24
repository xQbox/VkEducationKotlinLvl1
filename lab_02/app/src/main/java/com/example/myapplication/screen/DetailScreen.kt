package com.example.myapplication.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.myapplication.views.MainScreenViewModel


@Composable
fun DetailScreen(mainScreenViewModel: MainScreenViewModel) {
    val details = mainScreenViewModel.selectedImageDetail
//    val description = mainScreenViewModel.getDescription
    details?.let { (imageName, artistName, price, genre, stockCount) ->
        Box()
        {
            Text("Красивое описание для фотографии ")
//            Text(text = description)
        }
    }
}