package com.example.myapplication.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ElementData.ElementData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MainScreenViewModel : ViewModel() {

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _cultData = MutableStateFlow<List<ElementData>>(emptyList())
    val cultData: StateFlow<List<ElementData>> = _cultData.asStateFlow()
    private val _isLoadingCult = MutableStateFlow(true)
    val isLoadingCult: StateFlow<Boolean> = _isLoadingCult.asStateFlow()

    private val _editorChoiceData = MutableStateFlow<List<ElementData>>(emptyList())
    val editorChoiceData: StateFlow<List<ElementData>> = _editorChoiceData.asStateFlow()
    private val _isLoadingEditorChoice = MutableStateFlow(true)
    val isLoadingEditorChoice: StateFlow<Boolean> = _isLoadingEditorChoice.asStateFlow()

    private val _incredibleCultData = MutableStateFlow<List<ElementData>>(emptyList())
    val incredibleCultData: StateFlow<List<ElementData>> = _incredibleCultData.asStateFlow()
    private val _isLoadingIncredibleCult = MutableStateFlow(true)
    val isLoadingIncredibleCult: StateFlow<Boolean> = _isLoadingIncredibleCult.asStateFlow()

    init {
        fetchCultData()
    }

    val cultSectionData = listOf(
        ElementData(
            imageFileName = "2.jpg",
            artistName = "Pink Floyd",
            price = 19.99,
            genre = "Progressive Rock",
            quantityInStock = 10
        ),
        ElementData(
            imageFileName = "3.png",
            artistName = "The Beatles",
            price = 22.50,
            genre = "Rock",
            quantityInStock = 8
        ),
        ElementData(
            imageFileName = "4.jpg",
            artistName = "Michael Jackson",
            price = 18.99,
            genre = "Pop",
            quantityInStock = 12
        ),
        ElementData(
            imageFileName = "5.jpg",
            artistName = "Nirvana",
            price = 20.00,
            genre = "Grunge",
            quantityInStock = 15
        ),
        ElementData(
            imageFileName = "6.jpg",
            artistName = "AC/DC",
            price = 21.99,
            genre = "Hard Rock",
            quantityInStock = 5
        )
    )

    private fun fetchCultData() {
        viewModelScope.launch {
            _isLoadingCult.value = true
            delay(500)
            try {
//                val data = listOf<ElementData>()
                val data = cultSectionData
                _cultData.value = data
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoadingCult.value = false
            }
        }
    }
    private fun fetchEditorChoiceData() {
        viewModelScope.launch {
            _isLoadingEditorChoice.value = true
            delay(600)
            try {
                val data = cultSectionData
                _editorChoiceData.value = data
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoadingEditorChoice.value = false
            }
        }
    }
    private fun fetchIncredibleCultData() {
        viewModelScope.launch {
            _isLoadingIncredibleCult.value = true
            delay(700)
            try {
                val data = cultSectionData
                _incredibleCultData.value = data
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoadingIncredibleCult.value = false
            }
        }
    }

}