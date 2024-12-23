package com.example.myapplication.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomNavigationBox(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(Color(0xFF2C2C2C)),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavItem(
            label = "Главная",
            isSelected = currentRoute == Screen.Main.route,
            onClick = { myNavigateToRoute(navController, Screen.Main.route, currentRoute) }
        )
        NavItem(
            label = "Корзина",
            isSelected = currentRoute == Screen.Cart.route,
            onClick = { myNavigateToRoute(navController, Screen.Cart.route, currentRoute) }
        )
        NavItem(
            label = "Избранное",
            isSelected = currentRoute == Screen.Favourite.route,
            onClick = { myNavigateToRoute(navController, Screen.Favourite.route, currentRoute) }
        )
    }
}

@Composable
fun NavItem(label: String, isSelected: Boolean, onClick: () -> Unit) {
    val textColor = if (isSelected) Color(0xFFFF6A00) else Color.White
    val indicatorColor = if (isSelected) Color(0xFFFF6A00) else Color.Transparent
//    var textWidth = remember{mutableStateOf(0)}

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label, fontSize = 16.sp, color = textColor,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .height(2.dp)
                .width(30.dp) // TODO(Сделать динамически изменяемую ширину для каждого размера label)
                .background(indicatorColor)
        )
    }
}

fun myNavigateToRoute(navController: NavHostController, route: String, currentRoute: String?) {
    if (currentRoute != route) {
        navController.navigate(route) {
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}