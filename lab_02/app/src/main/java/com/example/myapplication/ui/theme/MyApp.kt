package com.example.myapplication.ui.theme
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.*
import com.example.myapp.com.example.myapplication.FilterAndSearch
import com.example.myapp.com.example.myapplication.Header
import com.example.myapp.com.example.myapplication.Logo
import com.example.myapp.com.example.myapplication.MainScroll
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

