package com.tematikhonov.cinemasearcher.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tematikhonov.cinemasearcher.R
import com.tematikhonov.cinemasearcher.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(savedInstanceState==null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commit()
        }
    }
}