package com.example.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bmiButton, caloriesButton, recipesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        bmiButton = findViewById(R.id.button_bmi);
        caloriesButton = findViewById(R.id.button_calories);
        recipesButton = findViewById(R.id.button_recipes);

        bmiButton.setOnClickListener(this);
        caloriesButton.setOnClickListener(this);
        recipesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_bmi:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.button_calories:
                startActivity(new Intent(this, KcalCalculator.class));
                break;
            case R.id.button_recipes:
                startActivity(new Intent(this, Recipes.class));
                break;
        }
    }
}