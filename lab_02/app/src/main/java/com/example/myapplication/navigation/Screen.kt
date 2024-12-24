package com.example.myapplication.navigation

sealed class Screen(val route: String, val titleResourceId: Int) {
    object Main         : Screen("MainScreen", -1)
    object Favourite    : Screen("FavouriteScreen", -1)
    object Cart         : Screen("DartScreen", -1)
    object DetailScreen : Screen("DetailScreen", -1)
}