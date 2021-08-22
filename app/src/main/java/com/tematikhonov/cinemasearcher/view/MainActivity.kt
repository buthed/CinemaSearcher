package com.tematikhonov.cinemasearcher.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tematikhonov.cinemasearcher.R
import com.tematikhonov.cinemasearcher.databinding.ActivityMainBinding
import com.tematikhonov.cinemasearcher.view.main.MainFragment

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

        //Initialize the bottom navigation view
        //create bottom navigation view object
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.container)
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_main,
                R.id.nav_search,
                R.id.nav_favorites,
                R.id.nav_history,
                R.id.nav_login))
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)
    }
}