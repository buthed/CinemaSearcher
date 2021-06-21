package com.tematikhonov.cinemasearcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tematikhonov.cinemasearcher.framework.ui.main_fragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}