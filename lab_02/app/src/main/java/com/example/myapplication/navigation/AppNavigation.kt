package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.screen.CartScreen
import com.example.myapplication.screen.DetailScreen
import com.example.myapplication.screen.FavouriteScreen
import com.example.myapplication.screen.MainScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Main.route)
    {
        composable(Screen.Main.route) { MainScreen(navController) }
        composable(Screen.Favourite.route){ FavouriteScreen() }
        composable(Screen.Cart.route) { CartScreen() }
        composable(
            route = "detail_screen/{imageName}/{artistName}/{price}/{genre}/{stockCount}",
            arguments = listOf(
                navArgument("imageName") { type = NavType.StringType },
                navArgument("artistName") { type = NavType.StringType },
                navArgument("price") { type = NavType.StringType },
                navArgument("genre") { type = NavType.StringType },
                navArgument("stockCount") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val imageName = backStackEntry.arguments?.getString("imageName") ?: ""
            val artistName = backStackEntry.arguments?.getString("artistName") ?: ""
            val price = backStackEntry.arguments?.getString("price") ?: "0.0"
            val genre = backStackEntry.arguments?.getString("genre") ?: ""
            val stockCount = backStackEntry.arguments?.getString("stockCount") ?: "0"

            DetailScreen(
                imageName = imageName,
                artistName = artistName,
                price = price,
                genre = genre,
                stockCount = stockCount,
                navController = navController
            )
        }
    }
}
