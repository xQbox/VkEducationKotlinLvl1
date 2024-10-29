package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.ArrayList
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity()
{

    lateinit var recyclerView: RecyclerView
    lateinit var fab: FloatingActionButton

    private val adapter = MyAdapter()
    companion object
    {
        val saveStringKey = "savedSize";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        recyclerView = findViewById(R.id.recycler_view)
        fab = findViewById(R.id.fab)

        recyclerView.adapter = adapter

        fab.setOnClickListener {
                adapter.addItems(adapter.itemCount + 1);

        }

        if (savedInstanceState != null)
        {
            adapter.setItems((1..savedInstanceState.getInt(saveStringKey)).toList());
        }
        else
        {
            adapter.setItems(listOf(1,2,3,4,5));
        }

    }

    override fun onSaveInstanceState(outState: Bundle)
    {
        super.onSaveInstanceState(outState)
        outState.putInt(saveStringKey, adapter.itemCount);
    }
}