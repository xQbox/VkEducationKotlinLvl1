// ItemsListScreen.kt
package com.example.myapp.com.example.myapplication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myapplication.Item
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.myapp.DataRepository
import com.example.myapplication.R
import java.util.logging.Filter


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun MainScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2C2C2C))
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            // Прокручиваемая часть с Logo
            item {
                Logo()
            }

            // StickyHeader для фиксированного Header
            stickyHeader {
                Header()
                FilterAndSearch()
            }
            // Основной прокручиваемый контент
            item {
                MainScroll()
            }
            // StickyHeader для фиксированного Header
//            stickyHeader {
//                FilterAndSearch()
//            }
        }
    }
}
//@Composable
//fun MainScreen() {
//    val scrollState = rememberScrollState() // Скролл состояние для всего контента
//    Box(modifier = Modifier
//        .fillMaxSize()
//        .background(Color(0xFF2C2C2C))
//    ) {
//        // Nested Scroll API
//        val lazyListState = rememberLazyListState()
//
//        // Создание всей структуры
//        LazyColumn(
//            state = lazyListState,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            // Верхние элементы (они будут двигаться вверх)
//            item {
//                Column(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Logo()
//                    Header() // Заголовок ниже логотипа
//                    FilterAndSearch()
//                }
//            }
//            // Контент, который прокручивается ниже
//            item {
//                MainScroll() // Лента с альбомами
//            }
//        }
//    }
//}

//@Composable
//fun MainScreen() {
//    Box(modifier = Modifier
//        .fillMaxSize()
//        .background(Color(0xFF2C2C2C))
//    )
//    {
//
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Top, // Элементы будут размещены сверху вниз
//            horizontalAlignment = Alignment.CenterHorizontally // Центрирование элементов по горизонтали
//        ) {
//            shadowLayout()
//            Header() // Заголовок ниже логотипа
//            FilterAndSearch()
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .weight(1f) // Занимает оставшееся пространство
//            ) {
//                MainScroll() // Обновляем функцию для поддержки прокрутки
//            }
////            MainScroll()
//        }
//    }
//}


@Composable
fun Logo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.width(55.dp))
        Icon(
            painter = AssetImagePainter("1.png"), // Замените "logo.png" на имя файла с логотипом
            contentDescription = "App Logo",
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
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
            .fillMaxWidth(),
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
        ) { Text("Собрания") }

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
            .padding(horizontal = 8.dp, vertical = 8.dp), // Added padding to the Row
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Кнопка "Фильтр" with weight
        Button(
            onClick = { /* Фильтр */ },
            modifier = Modifier
                .height(48.dp)
                .weight(0.3f), // Assign weight to the Button
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

        Spacer(modifier = Modifier.width(4.dp)) // Adjust spacer as needed

        // Поле ввода с иконкой with weight
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

//@Composable
//fun MainScroll() {
//    // Добавляем прокрутку с помощью LazyColumn
//    LazyColumn(
//        modifier = Modifier
//            .background(Color(0xFF2C2C2C))
//            .fillMaxSize(), // Заполняем весь доступный размер
//        verticalArrangement = Arrangement.spacedBy(16.dp), // Добавляем отступы между элементами
//        contentPadding = PaddingValues(vertical = 16.dp) // Отступы сверху и снизу
//    ) {
//        // Первый раздел "Культовое"
//        item {
//            SectionHeader(title = "Культовое")
//        }
//        item {
//            AlbumRow(
//                albums = listOf(
//                    Album("AC/DC (ост \"Iron Man 2\")", "Блюз-рок, хард-рок", 5400, 152),
//                    Album("Royal Blood — \"How Did We Get So Dark?\"", "гаражный рок, альтернативный рок", 2200, 0)
//                )
//            )
//        }
//
//        // Второй раздел "Выбор редакции"
//        item {
//            SectionHeader(title = "Выбор редакции")
//        }
//        item {
//            AlbumRow(
//                albums = listOf(
//                    Album("AC/DC (ост \"Iron Man 2\")", "Блюз-рок, хард-рок", 5400, 152),
//                    Album("Royal Blood — \"How Did We Get So Dark?\"", "гаражный рок, альтернативный рок", 2200, 0)
//                )
//            )
//        }
//    }
//}

@Composable
fun SectionHeader(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun MainScroll() {
    Column(
        modifier = Modifier
            .background(Color(0xFF2C2C2C))
            .fillMaxSize(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(), // Растянуть Row на всю ширину
            horizontalArrangement = Arrangement.Center // Разместить содержимое по центру горизонтально
        ) {
            Text(
                text = "Культовое",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 16.dp), // Отступы сверху и снизу
                textAlign = TextAlign.Center, // Центрирует текст внутри элемента Text
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        )
        {
            AlbumRow(
                albums = listOf(
                    Album("AC/DC (ост \"Iron Man 2\")", "Блюз-рок, хард-рок", 5400, 152),
                    Album("Royal Blood — \"How Did We Get So Dark?\"", "гаражный рок, альтернативный рок", 2200, 0)
            )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(), // Растянуть Row на всю ширину
            horizontalArrangement = Arrangement.Center // Разместить содержимое по центру горизонтально
        ) {
            Text(
                text = "Выбор редакции",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 16.dp), // Отступы сверху и снизу
                textAlign = TextAlign.Center, // Центрирует текст внутри элемента Text
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        )
        {
            AlbumRow(
                albums = listOf(
                    Album("AC/DC (ост \"Iron Man 2\")", "Блюз-рок, хард-рок", 5400, 152),
                    Album("Royal Blood — \"How Did We Get So Dark?\"", "гаражный рок, альтернативный рок", 2200, 0)
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(), // Растянуть Row на всю ширину
            horizontalArrangement = Arrangement.Center // Разместить содержимое по центру горизонтально
        ) {
            Text(
                text = "Выбор редакции",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 16.dp), // Отступы сверху и снизу
                textAlign = TextAlign.Center, // Центрирует текст внутри элемента Text
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        )
        {
            AlbumRow(
                albums = listOf(
                    Album("AC/DC (ост \"Iron Man 2\")", "Блюз-рок, хард-рок", 5400, 152),
                    Album("Royal Blood — \"How Did We Get So Dark?\"", "гаражный рок, альтернативный рок", 2200, 0)
                )
            )
        }
    }
}

@Composable
fun AlbumRow(albums: List<Album>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()), // Добавляем горизонтальную прокрутку
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Расстояние между элементами
    ) {
        albums.forEach { album ->
            Box(
                modifier = Modifier
                    .size(200.dp) // Задаем размер для Box
                    .background(Color.LightGray), // Фон для наглядности
                contentAlignment = Alignment.Center // Выравнивание содержимого
            ) {
                Text(text = "Album", color = Color.White) // Пример содержимого Box
            }
        }
    }
}

@Composable
fun ProductBox() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val context = LocalContext.current

    // Определяем размеры в зависимости от платформы
    val boxSize = if (screenWidth < 600.dp) {
        150.dp
    } else {
        200.dp
    }

    Column(
        modifier = Modifier
            .background(Color(0xFF333333))
    ) {
        Box(
            modifier = Modifier
                .size(boxSize)
                .background(Color.White, shape = RoundedCornerShape(8.dp))
        ) {
            Image(
                painter = AssetImagePainter("3.png"),
                contentDescription = "AlbumCover",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            IconButton(
                onClick = {
                    // Логика добавления в корзину
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .background(Color.Black, shape = RoundedCornerShape(50))
            ) {
                Icon(
                    painter = AssetImagePainter("1.png"),
                    contentDescription = "AddtoCart",
                    tint = Color.White
                )
            }
        }

        Box(
            modifier = Modifier.background(Color(0xFFFF6A00), shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.CenterStart

        ) {
            Column {
                Text(text = "AC/DC (ost 'Iron Man 2')", color = Color.White, fontSize = 16.sp)
                Text(
                    text = "Жанр: блоз-рок, хард-рок",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "5400 ₽", color = Color.White, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Товаров в наличии: 152",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun AlbumCard(album: Album) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(Color(0xFFFF6A00), shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
            .width(150.dp)
    ) {
        // Mock image
        Box(modifier = Modifier.height(100.dp).background(Color.Gray))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = album.title, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Text(text = "Жанр: ${album.genre}", color = Color.White, fontSize = 10.sp)
        Text(text = "${album.price}₽", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        Text(text = "Товаров в наличии: ${album.stock}", color = Color.White, fontSize = 10.sp)
    }
}

data class Album(val title: String, val genre: String, val price: Int, val stock: Int)

//// ItemsListScreen.kt
//package com.example.myapp.com.example.myapplication
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import com.example.myapplication.Item
//import coil.compose.AsyncImage
//import com.example.myapp.DataRepository
//
//
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
//

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
