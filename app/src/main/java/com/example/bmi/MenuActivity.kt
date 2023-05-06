package com.example.bmi

import BmiChartActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity(), View.OnClickListener {

private lateinit var bmiButton: Button
private lateinit var caloriesButton: Button
private lateinit var recipesButton: Button
private lateinit var quizButton: Button
private lateinit var bmiChart: Button

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)

        bmiButton = findViewById(R.id.button_bmi)
        caloriesButton = findViewById(R.id.button_calories)
        recipesButton = findViewById(R.id.button_recipes)
        quizButton = findViewById(R.id.button_Quiz)
        bmiChart = findViewById(R.id.button_chart)

        bmiButton.setOnClickListener(this)
        caloriesButton.setOnClickListener(this)
        recipesButton.setOnClickListener(this)
        quizButton.setOnClickListener(this)
        bmiChart.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
        when (v?.id) {
        R.id.button_bmi -> startActivity(Intent(this, MainActivity::class.java))
        R.id.button_calories -> startActivity(Intent(this, KcalCalculator::class.java))
        R.id.button_recipes -> startActivity(Intent(this, Recipes::class.java))
        R.id.button_Quiz -> startActivity(Intent(this, Quiz::class.java))
        R.id.button_chart -> startActivity(Intent(this, BmiChartActivity::class.java))
        }
        }
        }