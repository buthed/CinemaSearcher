package com.tematikhonov.cinemasearcher

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tematikhonov.cinemasearcher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.firstButton.setOnClickListener {
            Log.d("LOG", "clicked")
            Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT)
                    .show()
        }

    }
}