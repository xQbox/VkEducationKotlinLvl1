// DataRepository.kt
package com.example.myapplication.frontendPart

import android.content.Context
import android.util.Log
import kotlinx.coroutines.delay
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream


object DataRepository {
    // Основная функция получения данных
    suspend fun getItems(context: Context, delayMillis: Long = 0): List<Item> {
        delay(delayMillis)
        copyImagesToInternalStorage(context)  // Копируем изображения из assets в внутреннее хранилище

        val imagesDir = File(context.filesDir, "images")
        val imageFiles: Array<File> = imagesDir.listFiles()?.sortedBy { it.name }?.toTypedArray() ?: arrayOf()

        if (!imagesDir.exists() || imagesDir.listFiles().isNullOrEmpty())
        {
            // Логирование или обработка ошибки
            println("Папка 'images' пуста или не существует.")
        }

        val names =   "Здесь название фотографии "// context.resources.getStringArray(R.array.item_names)
        val descriptions =  "Здесь описание для фотографии" //context.resources.getStringArray(R.array.item_descriptions)

        val items = names.indices.map { index ->
            val imageFile = imageFiles.getOrNull(index)
            Item(
                id = index,
                name = names,
                description = descriptions,
                imagePath = imageFile?.absolutePath ?: ""
            )
        }
        return items
    }

    suspend fun getItemById(context: Context, itemId: Int, delayMillis: Long = 0): Item? {
        delay(delayMillis)
        val items = getItems(context, delayMillis = 0)
        return items.find { it.id == itemId }
    }

    // Функция для копирования изображений из assets во внутреннее хранилище
    private fun copyImagesToInternalStorage(context: Context) {
        val imagesDir = File(context.filesDir, "images")
        if (!imagesDir.exists()) {
            imagesDir.mkdir() // Создаем директорию, если она не существует
        }
        // Список изображений в assets
        val assetImages = listOf("1.png", "2.jpg", "3.png","4.jpg", "5.jpg", "6.jpg" ) // Путь к изображениям в assets
        val assetFiles = context.assets.list("") // Получаем список файлов в папке assets
        assetFiles?.forEach { file ->
            Log.d("DataRepository", "Найден файл в assets: $file")
        }
        for ((index, assetName) in assetImages.withIndex())
        {
            try {
                val inputStream: InputStream = context.assets.open(assetName)
                val outputFile = File(imagesDir, "image_$index.png")
                val outputStream = outputFile.outputStream()

                inputStream.use { input ->
                    outputStream.use { output ->
                        input.copyTo(output)
                    }
                }
                Log.d("DataRepository", "Изображение ${assetName.substringAfterLast('/')} скопировано.")
            } catch (e: FileNotFoundException) {
                Log.e("DataRepository", "Файл $assetName не найден в assets", e)
            } catch (e: Exception) {
                Log.e("DataRepository", "Ошибка копирования изображения $assetName", e)
            }
        }
    }
}

