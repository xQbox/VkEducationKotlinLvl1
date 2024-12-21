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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myapplication.frontendPart.Item
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.R
import com.example.myapplication.frontendPart.DataRepository
import com.example.myapplication.screen.screenExtenderdata

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyApp(modifier: Modifier, screenState : screenExtenderdata) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            Logo()
        }
        stickyHeader {
            Header()
            FilterAndSearch()
        }
        item {
            MainScroll()
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
fun MainScroll() {
    Column(
        modifier = Modifier
            .background(Color(0xFF2C2C2C))
            .fillMaxSize(),
    ) {
        // catalogHeader
        Box(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Культовое",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
        }

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
        // catalogHeader
        Box(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
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
                .fillMaxWidth(),
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
        ProductBox()
        ProductBox()
        ProductBox()
        ProductBox()
    }
}

//@Preview
@Composable
fun ProductBox() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp // Ширина экрана в dp
    val buttonSize = if (screenWidth < 600.dp) 40.dp else 60.dp

    Column(
        modifier = Modifier
            .padding(4.dp)
            .width(200.dp) // Занимаем всю ширину
            .height(300.dp) // Ограничиваем высоту
    ) {
        // Первый Box (0.8f пространства)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f) // 80% пространства
                .aspectRatio(1f) // Квадратный Box
                .padding(bottom = 4.dp)
        ) {
            Image(
                painter = AssetImagePainter("3.png"),
                contentDescription = "AlbumCover",
                modifier = Modifier
                    .fillMaxSize(0.8f)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(15))
                    .background(Color.White),
                contentScale = ContentScale.Crop // Масштабирование изображения
            )
            IconButton(
                onClick = {
                    // Логика добавления в корзину
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(buttonSize) // Размер кнопки
                    .offset(x = (-10).dp, y = (-10).dp) // Смещение кнопки
                    .background(Color.Yellow) // Круглый фон
            ) {
                Icon(
                    painter = AssetImagePainter("1.png"),
                    contentDescription = "Add to Cart",
                    tint = Color.White
                )
            }
        }

        // Второй Box (0.2f пространства)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f) // 20% пространства
                .clip(RoundedCornerShape(13))
                .background(Color(0xFFFF6A00))
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Eminem - \"Street Rap\"",
                        color = Color.White,
                        fontSize = 8.sp,
                        modifier = Modifier.weight(1f),
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = "5400 ₽",
                        color = Color.White,
                        fontSize = 8.sp,
                        modifier = Modifier.align(Alignment.CenterVertically),
                    )
                }
                Text(
                    text = "Жанр: блоз-рок, хард-рок",
                    color = Color.White,
                    fontSize = 7.sp,
                    overflow = TextOverflow.Ellipsis,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Товаров в наличии:",
                        color = Color.White,
                        fontSize = 8.sp,
                        modifier = Modifier.weight(1f),
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = "15",
                        color = Color.White,
                        fontSize = 8.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
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


