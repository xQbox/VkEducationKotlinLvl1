package com.example.myapplication.screen


import android.annotation.SuppressLint
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
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
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import com.example.myapplication.frontendPart.Item
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.ElementData.ElementData
import com.example.myapplication.R
import com.example.myapplication.frontendPart.DataRepository
import com.example.myapplication.views.MainScreenViewModel
import com.google.accompanist.placeholder.PlaceholderHighlight


@Composable
fun MainScreen(viewModel: MainScreenViewModel = viewModel()) {
    val cultData by viewModel.cultData.collectAsState()
    val isLoadingCult by viewModel.isLoadingCult.collectAsState()
    val editorChoiceData by viewModel.editorChoiceData.collectAsState()
    val isLoadingEditorChoice by viewModel.isLoadingEditorChoice.collectAsState()
    val incredibleCultData by viewModel.incredibleCultData.collectAsState()
    val isLoadingIncredibleCult by viewModel.isLoadingIncredibleCult.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Logo()
            }
            stickyHeader {
                Header()
                FilterAndSearch()
            }
            item {
                ScrollElement("Культовое", cultData, isLoadingCult, errorMessage, {viewModel.fetchCultData()})
                ScrollElement("Выбор Редакции", editorChoiceData, isLoadingEditorChoice, errorMessage, {viewModel.fetchEditorChoiceData()})
                ScrollElement("Невероятно культовое", incredibleCultData, isLoadingIncredibleCult, errorMessage, {viewModel.fetchIncredibleCultData()})
            }
        }
    }
}

@Composable
fun Logo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2C2C2C)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.width(55.dp))
        Image(
            painter =  painterResource(R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
        )
        // Название приложения
        Text(
            text = "VINIL-CLUB",
            style = MaterialTheme.run {
                typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp, // Увеличиваем размер шрифта
                    color = Color.White
                )
            },
            modifier = Modifier
                .padding(start = 8.dp)
        )
        Spacer(modifier = Modifier.width(35.dp))
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2C2C2C)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly // Равномерное распределение кнопок
    ) {
        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = { /* Навигация */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF6A00),
                contentColor = Color.White
            ),
        ) { Text("Сборники") }

        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = { /* Навигация */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF6A00),
                contentColor = Color.White
            ),
        ) { Text("Каталог") }

        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = { /* Навигация */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF6A00),
                contentColor = Color.White
            ),
        ) { Text("Категории") }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterAndSearch() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2C2C2C))
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { /* Фильтр */ },
            modifier = Modifier
                .height(48.dp)
                .weight(0.3f),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            border = BorderStroke(2.dp, Color(0xFFFF6A00))
        ) {
            Text("Фильтр")
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Dropdown",
                modifier = Modifier.size(16.dp)
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        TextField(
            value = "",
            onValueChange = { /* Обработка ввода */ },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Black
                )
            },
            placeholder = {
                Text("Поиск пластинки", color = Color.Gray)
            },
            modifier = Modifier
                .weight(0.7f) // Assign weight to the TextField
                .height(52.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}
@Composable
fun ScrollElement(
    sectionTitle: String,
    data: List<ElementData>,
    isLoading: Boolean,
    errorMessage: String?,
    onLoadMore: () -> Unit,
) {
    val listState = rememberLazyListState()

    // Проверка на загрузку доп. контента при прокрутке
    val shouldLoadMore = remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val totalItemsCount = layoutInfo.totalItemsCount
            val visibleItems = layoutInfo.visibleItemsInfo
            if (visibleItems.isNotEmpty()) {
                val lastVisibleItemIndex = visibleItems.last().index
                lastVisibleItemIndex >= totalItemsCount - 3
            } else {
                false
            }
        }
    }

    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value && !isLoading && errorMessage == null) {
            onLoadMore()
        }
    }

    Column(
        modifier = Modifier
            .background(Color(0xFF2C2C2C))
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = sectionTitle,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        when {
            errorMessage != null -> {
                Text(
                    text = "Ошибка: $errorMessage",
                    modifier = Modifier.padding(16.dp),
                    color = Color.White
                )
            }

            data.isNotEmpty() -> {
                Box {
                    LazyRow(
                        state = listState,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        items(data) { element ->
                            ProductBox(element)
                        }
                        if (isLoading) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .size(width = 150.dp, height = 200.dp)
                                        .padding(4.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }
                }
            }

            isLoading -> {
                LazyRow(
                    state = listState,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(15) {
                        SkeletonElement()
                    }
                }
            }

            else -> {
                Text(
                    text = "Данных нет",
                    modifier = Modifier.padding(16.dp),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun SkeletonElement() {
    Column(
        modifier = Modifier
            .width(180.dp)
            .height(200.dp)
            .placeholder(
                visible = true,
                highlight = PlaceholderHighlight.shimmer(),
                color = Color.LightGray,
                shape = RoundedCornerShape(16.dp)
            )
    ) {}
}

@Composable
fun ProductBox(elementData: ElementData) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val buttonSize = if (screenWidth < 600.dp) 30.dp else 40.dp
    val (imageName, artistName, price, genre, stockCount) = elementData

    Column(
        modifier = Modifier
            .width(200.dp)
            .height(300.dp)
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        // Логика нажатия
                    },
//                    indication = rememberRipple(bounded = false),
//                    interactionSource = remember { MutableInteractionSource() }
                )
        ) {
            Image(
                painter = AssetImagePainter(imageName),
                contentDescription = "AlbumCover",
                modifier = Modifier
                    .fillMaxSize(0.93f)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(15))
                    .background(Color.White),
//                    .clickable{ navController.navigate("detail_screen")},
                contentScale = ContentScale.Crop // Масштабирование изображения
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(buttonSize)
                    .offset(y = (-30).dp)
                    .background(Color.Yellow)
                    .clickable(
                        onClick = {
                            // Логика добавления в корзину
                        },
//                        indication = rememberRipple(bounded = false),
//                        interactionSource = remember { MutableInteractionSource() }
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Add to Cart",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(13))
                .background(Color(0xFFFF6A00))
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            // Первая строка с двумя текстами
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = artistName,
                    color = Color.White,
                    fontSize = 10.sp,
                    lineHeight = 10.sp,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = price.toString(),
                    color = Color.White,
                    fontSize = 10.sp,
                    lineHeight = 10.sp,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Text(
                text = genre,
                color = Color.White.copy(alpha = 0.8f),
                lineHeight = 10.sp,
                fontSize = 10.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(4.dp))
            // Третья строка с двумя текстами
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Товаров в наличии:",
                    color = Color.White,
                    lineHeight = 10.sp,
                    fontSize = 10.sp,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = stockCount.toString(),
                    color = Color.White,
                    lineHeight = 10.sp,
                    fontSize = 10.sp,
                )
            }
        }
    }
}



data class Album(val title: String, val genre: String, val price: Int, val stock: Int)


@Composable
fun ItemsListScreen(navController: NavController) {
    val context = LocalContext.current

    // Состояние списка элементов
    var items by remember { mutableStateOf<List<Item>?>(null) }
    // Состояние загрузки
    var isLoading by remember { mutableStateOf(true) }

    // Эффект, который запускается при первом отображении
    LaunchedEffect(Unit) {
        // Получаем данные из репозитория с задержкой
        val data = DataRepository.getItems(context)
        items = data
        isLoading = false
    }

    // Отображаем индикатор загрузки или список элементов
    if (isLoading) {
        LoadingIndicator()
    } else {
        items?.let {
            ItemsList(it, navController)
        }
    }
}
//// Функция описывает
@Composable
fun LoadingIndicator() {
    // Центрируем индикатор загрузки на экране
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

//
@Composable
fun ItemsList(items: List<Item>, navController: NavController) {
    // Отображаем список с элементами
    LazyColumn {
        items(items) { item ->
            ItemRow(item = item, onClick = {
                // Переходим на экран деталей элемента
                navController.navigate("item_details/${item.id}")
            })
            Divider()
        }
    }
}

@Composable
fun ItemRow(item: Item, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        // Используем AsyncImage для отображения изображения из файла
        AsyncImage(
            model = item.imagePath,
            contentDescription = item.name,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = item.name, style = MaterialTheme.typography.titleMedium)
            Text(text = item.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun AssetImagePainter(imageFileName: String): Painter {
    return rememberAsyncImagePainter("file:///android_asset/$imageFileName")
}


