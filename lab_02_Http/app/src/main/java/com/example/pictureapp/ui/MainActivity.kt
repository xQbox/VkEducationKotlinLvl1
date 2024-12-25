package com.example.pictureapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pictureapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainFragment: MainFragment
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainFragment = MainFragment()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(binding.main.id,  mainFragment)
                .commit()
        }
    }
}