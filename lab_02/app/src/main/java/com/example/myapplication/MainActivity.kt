// MainActivity.kt
package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.navigation.AppNavigation
import com.example.myapplication.navigation.BottomNavigationBox
import com.example.myapplication.navigation.Screen
import com.example.myapplication.screen.CartScreen
import com.example.myapplication.screen.DetailScreen
import com.example.myapplication.screen.FavouriteScreen
import com.example.myapplication.screen.MainScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.views.MainScreenViewModel


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme() {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomNavigationBox(navController = navController) }
                )
                {
                    AppNavigation(navController)
                }
            }
        }
    }
}
