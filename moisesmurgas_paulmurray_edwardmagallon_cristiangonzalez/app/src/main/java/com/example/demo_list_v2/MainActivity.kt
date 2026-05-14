package com.example.demo_list_v2

/**
 * Moises Murgas	Cédula: 8-1003-179
 * Paul Murray  	Cédula: 8-1013-729
 * Edward Magallon	Cédula: 8-992-1241
 * Cristian Gonzalez	Cédula: 8-1020-424
 */

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val switch = findViewById<SwitchCompat>(R.id.switchDarkMode)
        switch.setOnCheckedChangeListener { _, isChecked ->
            val mode = if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                       else          AppCompatDelegate.MODE_NIGHT_NO
            AppCompatDelegate.setDefaultNightMode(mode)
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PlaylistFragment())
                .commit()
        }
    }
}
