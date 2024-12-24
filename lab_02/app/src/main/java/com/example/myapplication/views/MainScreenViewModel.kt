package com.example.myapplication.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.ElementData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MainScreenViewModel : ViewModel() {

    private val _description = MutableStateFlow<String?>(null)
    val description: StateFlow<String?> = _description.asStateFlow()
    private val _isLoadingDesc = MutableStateFlow(true)
    val isLoadingDesc = _isLoadingDesc.asStateFlow()

    var selectedImageDetail by mutableStateOf<ElementData?>(null)
        private set

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

    private var cultPage = 0
    private val pageSize = 10
    private val cultTotalPages = 5

    private var editorChoicePage = 0
    private val editorChoiceTotalPages = 7

    private var incredibleChoicePage = 0
    private val incredibleChoiceTotalPage = 3


    init {
        fetchCultData()
        fetchEditorChoiceData()
        fetchIncredibleCultData()
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

     fun fetchCultData() {
         if (cultPage >= cultTotalPages) return

         viewModelScope.launch {
            _isLoadingCult.value = true
            delay(1000)
            try {
                // TODO() Логика -> Запрос на сервер вместе с текущими деталями изображения
                // Получение описания для конкретной картинки
//                val data = listOf<ElementData>()
//                for (i in 0..cultSectionData.size) TODO(рандомный элементы)
                val newData = cultSectionData
                _cultData.value = newData + _cultData.value
                _errorMessage.value = null
                ++cultPage
            } catch (e: Exception) {
                _errorMessage.value = e.message // TODO(Сделать для каждого fetch отдельную переменную с сообщением об ошибках)
            } finally {
                _isLoadingCult.value = false
            }
        }
    }

    private fun getCultPageData(page: Int): List<ElementData> {
        val fromIndex = page * pageSize
        val toIndex = minOf(fromIndex + pageSize, cultSectionData.size)
        if (fromIndex >= cultSectionData.size) return emptyList()
        return cultSectionData.subList(fromIndex, toIndex)
    }

    fun fetchEditorChoiceData() {
        if (editorChoicePage >= editorChoiceTotalPages) return
        viewModelScope.launch {
            _isLoadingEditorChoice.value = true
            delay(500)
            try {
                // TODO() Логика -> Запрос на сервер вместе с текущими деталями изображения
                // Получение описания для конкретной картинки
                val newData = cultSectionData
                _editorChoiceData.value = newData + _editorChoiceData.value
                _errorMessage.value = null
                ++editorChoicePage
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoadingEditorChoice.value = false
            }
        }
    }

    fun fetchIncredibleCultData() {
        if (incredibleChoicePage >= incredibleChoiceTotalPage) return

        viewModelScope.launch {
            _isLoadingIncredibleCult.value = true
            delay(2000)
            try {
                // TODO() Логика -> Запрос на сервер вместе с текущими деталями изображения
                // Получение описания для конкретной картинки
                val newData = cultSectionData
                _incredibleCultData.value = newData + _incredibleCultData.value
                _errorMessage.value = null
                ++incredibleChoicePage
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoadingIncredibleCult.value = false
            }
        }
    }

    fun fetchDescription(elementData: ElementData) {
        viewModelScope.launch {
            _isLoadingDesc.value = true
            try {
                // TODO() Логика -> Запрос на сервер вместе с текущими деталями изображения
                // Получение описания для конкретной картинки
                val newDescription = elementData.genre + "Описание каждой конкретной фотографии"
                _description.value = newDescription
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoadingDesc.value = false
            }
        }
    }


    fun selectImageDetail(detail: ElementData) {
        selectedImageDetail = detail
    }
}