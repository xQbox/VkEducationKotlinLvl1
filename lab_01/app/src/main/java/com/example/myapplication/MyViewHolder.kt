package com.example.myapplication

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val text = view.findViewById<TextView>(R.id.text_1)
    val image = view.findViewById<ImageView>(R.id.image_view)

    var isClicked = false
    fun bind(number: Int)
    {
        text.text = "$number"
        setColorByNumber(number)
        itemView.setOnClickListener()
        {
            isClicked = !isClicked
            if (isClicked) {
                // Меняем цвет в зависимости от числа
                if (number % 2 == 1) {
                    image.setBackgroundColor(Color.RED)
                } else {
                    image.setBackgroundColor(Color.BLUE)
                }
            } else {
                // Возвращаем стандартный цвет, если элемент не нажат
                setColorByNumber(number)
            }

        }
    }

    fun setColorByNumber(number : Int)
    {
        if (number % 2 == 0)
        {
            image.setBackgroundColor(Color.RED)
        }
        else
        {
            image.setBackgroundColor(Color.BLUE)
        }
    }
}