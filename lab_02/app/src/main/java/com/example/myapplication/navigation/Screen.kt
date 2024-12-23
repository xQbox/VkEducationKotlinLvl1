package com.example.myapplication.navigation

sealed class Screen(val route: String, val titleResourceId: Int) {
    object Main         : Screen("mainScreen", -1)
    object Favourite    : Screen("favouriteScreen", -1)
    object Cart         : Screen("cartScreen", -1)
}