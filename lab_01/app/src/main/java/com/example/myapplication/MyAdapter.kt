package com.example.myapplication.com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class MyAdapter() : RecyclerView.Adapter<MyViewHolder>()
{
    private val items = ArrayList<Int>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        holder.ad
    }

    override fun getItemCount(): Int
    {
        return items.size
    }

}