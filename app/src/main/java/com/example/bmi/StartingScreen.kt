package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.os.Handler

class StartingScreen : AppCompatActivity() {
private val SPLASH_SCREEN_TIME_OUT = 2000 // 2 seconds

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starting_screen)

        Handler().postDelayed({
        val menuIntent = Intent(this@StartingScreen, MenuActivity::class.java)
        startActivity(menuIntent)
        finish()
        }, SPLASH_SCREEN_TIME_OUT.toLong())
        }
        }