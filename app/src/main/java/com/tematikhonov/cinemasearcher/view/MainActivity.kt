package com.tematikhonov.cinemasearcher.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tematikhonov.cinemasearcher.R
import com.tematikhonov.cinemasearcher.databinding.ActivityMainBinding
import com.tematikhonov.cinemasearcher.view.main.MainFragment

class MainActivity: AppCompatActivity() {

    val CHANNEL_ID1="fisrt_channel"
    val CHANNEL_ID2="second_channel"
    val NOTIFICATION_ID=1

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
                R.id.settings_login))
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)
        push()
    }

    private fun push() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID2).apply {
            setContentTitle("First notification")
            setContentText("Notification text")
            setSmallIcon(R.drawable.ic_kotlin_logo)
            setPriority(NotificationCompat.PRIORITY_DEFAULT)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel(notificationManager)
        }

        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun createChannel(notificationManager: NotificationManager) {
        val name = "Важное новый2"
        val description1 = "От этого зависит судьба Земли"
        val description2 = "Не выключай, Христа ради"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID2, name, importance).apply {
            description = description1
        }
        notificationManager.createNotificationChannel(channel)
    }
}