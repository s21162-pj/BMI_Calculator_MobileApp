package com.example.bmi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Recipe : AppCompatActivity() {
    private var recipeNameTextView: TextView? = null
    private var ingredientsTextView: TextView? = null
    private var directionsTextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        val intent = intent
        val recipeName = intent.getStringExtra("recipe_name")
        val ingredients = intent.getStringExtra("recipe_ingredients")
        val directions = intent.getStringExtra("recipe_directions")
        recipeNameTextView = findViewById<TextView>(R.id.textView_recipe_name)
        recipeNameTextView?.text = recipeName
        ingredientsTextView = findViewById(R.id.textView_recipe_ingredients)
        ingredientsTextView?.setText(ingredients)
        directionsTextView = findViewById(R.id.textView_recipe_directions)
        directionsTextView?.setText(directions)
    }
}