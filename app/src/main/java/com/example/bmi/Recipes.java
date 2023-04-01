package com.example.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class Recipes extends AppCompatActivity {

    private String[] recipeNames;
    private Map<String, String[]> recipeMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        // Get the recipe names and their corresponding ingredients and directions from the string resources
        recipeNames = getResources().getStringArray(R.array.list_of_recipes);
        String[] ingredients = getResources().getStringArray(R.array.recipe_ingredients);
        String[] directions = getResources().getStringArray(R.array.recipe_directions);

        // Create a map that links each recipe name to its corresponding ingredients and directions
        recipeMap = new HashMap<>();
        for (int i = 0; i < recipeNames.length; i++) {
            String recipeName = recipeNames[i];
            String[] recipeIngredients = ingredients[i].split("\\r?\\n");
            String[] recipeDirections = directions[i].split("\\r?\\n");
            recipeMap.put(recipeName, new String[]{TextUtils.join("\n", recipeIngredients), TextUtils.join("\n", recipeDirections)});
        }

        // Set up the ListView to display the recipe names
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recipeNames);
        ListView listView = findViewById(R.id.ListView_recipes);
        listView.setAdapter(adapter);

        // Set an item click listener for the ListView to launch the Recipe activity and pass the selected recipe's ingredients and directions
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String recipeName = recipeNames[position];
                String[] recipeData = recipeMap.get(recipeName);
                Intent intent = new Intent(Recipes.this, Recipe.class);
                intent.putExtra("recipe_name", recipeName);
                intent.putExtra("recipe_ingredients", recipeData[0]);
                intent.putExtra("recipe_directions", recipeData[1]);
                startActivity(intent);
            }
        });
    }
}