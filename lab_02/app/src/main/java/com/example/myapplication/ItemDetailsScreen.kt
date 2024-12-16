package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.myapp.DataRepository
import com.example.myapp.com.example.myapplication.LoadingIndicator


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailsScreen(itemId: Int, navController: NavController) {
    val context = LocalContext.current

    // Состояние для элемента и состояния загрузки
    var item by remember { mutableStateOf<Item?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    // Эффект, который запускается при изменении itemId
    LaunchedEffect(itemId) {
        val dataItem = DataRepository.getItemById(context, itemId)
        item = dataItem
        isLoading = false
    }

    // Используем Scaffold для организации экрана
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = item?.name ?: "") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        // Отображаем индикатор загрузки или детали элемента
        if (isLoading) {
            LoadingIndicator()
        } else {
            item?.let {
                ItemDetails(it, modifier = Modifier.padding(paddingValues))
            }
        }
    }
}

@Composable
fun ItemDetails(item: Item, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Используем AsyncImage для отображения изображения из файла
        AsyncImage(
            model = item.imagePath,
            contentDescription = item.name,
            modifier = Modifier.size(128.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = item.name, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = item.description, style = MaterialTheme.typography.bodyLarge)
    }
}
