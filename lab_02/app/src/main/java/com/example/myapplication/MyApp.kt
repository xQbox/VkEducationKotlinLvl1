package com.example.myapplication
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.myapp.com.example.myapplication.ItemsListScreen
import com.example.myapp.com.example.myapplication.MainScreen
import com.example.myapplication.ItemDetailsScreen
import com.example.myapplication.ui.theme.MyApplicationTheme

//@Composable
//fun MyApp() {
//    MyApplicationTheme {
//        // Создаем контроллер навигации
//        val navController = rememberNavController()
//
//        // Настраиваем граф навигации
//        NavHost(navController, startDestination = "items_list") {
//            composable("items_list") {
//                ItemsListScreen(navController = navController)
//            }
//            composable("item_details/{itemId}") { backStackEntry ->
//                val itemId = backStackEntry.arguments?.getString("itemId")?.toIntOrNull()
//                itemId?.let {
//                    ItemDetailsScreen(itemId = it, navController = navController)
//                }
//            }
//        }
//    }
//}

@Composable
fun MyApp() {
    MyApplicationTheme {
        val navController = rememberNavController()

        NavHost(navController, startDestination = "main_screen") {
            composable("main_screen") {
                MainScreen()
            }
            composable("items_list") {
                ItemsListScreen(navController = navController)
            }
            composable("item_details/{itemId}") { backStackEntry ->
                val itemId = backStackEntry.arguments?.getString("itemId")?.toIntOrNull()
                itemId?.let {
                    ItemDetailsScreen(itemId = it, navController = navController)
                }
            }
        }
    }
}

