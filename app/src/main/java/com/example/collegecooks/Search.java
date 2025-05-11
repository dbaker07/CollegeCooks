package com.example.collegecooks;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View; // Import View
import android.widget.AdapterView; // Import AdapterView
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Search extends AppCompatActivity {
    private RecipeRetriever recipeRetriever;
    private List<Recipe> allRecipes; // Keep this to hold the loaded recipes
    private ListView recipeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipeRetriever = new RecipeRetriever();
        setContentView(R.layout.search_page);
        recipeListView = findViewById(R.id.recipeListView);
        SearchView searchbar = findViewById(R.id.search);

        // 1. Set the callback before calling retrieveAllRecipes
        recipeRetriever.setRecipeCallback(new RecipeRetriever.RecipeCallback() {
            @Override
            public void onRecipesLoaded(List<Recipe> recipes) {
                // 2. This is where you get the loaded recipes
                allRecipes = recipes; // Assign the loaded recipes to the class variable
                updateListView(allRecipes); // Update the UI with the loaded recipes
            }

            @Override
            public void onRecipesLoadFailed(String errorMessage) {
                // Handle the error, e.g., show a Toast message
                Log.e("SearchActivity", "Failed to load recipes: " + errorMessage);
                Toast.makeText(Search.this, "Failed to load recipes: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        // 3. Start the asynchronous recipe loading process
        recipeRetriever.retrieveAllRecipes();


        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Make sure allRecipes is not null before searching
                if (allRecipes != null) {
                    List<Recipe> filteredRecipes = recipeRetriever.searchRecipes(allRecipes, query);
                    updateListView(filteredRecipes);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Make sure allRecipes is not null before searching
                if (allRecipes != null) {
                    List<Recipe> filteredRecipes = recipeRetriever.searchRecipes(allRecipes, newText);
                    updateListView(filteredRecipes);
                }
                return true;
            }
        });

        // Add the OnItemClickListener to the ListView
        recipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked recipe name from the adapter
                String clickedRecipeName = (String) parent.getItemAtPosition(position);

                // Find the corresponding Recipe object from your allRecipes list
                Recipe selectedRecipe = null;
                if (allRecipes != null) {
                    for (Recipe recipe : allRecipes) {
                        if (recipe.getName().equals(clickedRecipeName)) {
                            selectedRecipe = recipe;
                            break;
                        }
                    }
                }

                // If a recipe is found, start InRecipeActivity and pass the recipe data
                if (selectedRecipe != null) {
                    Intent intent = new Intent(Search.this, InRecipe.class);
                    intent.putExtra("recipe_name", selectedRecipe.getName());

                    startActivity(intent);
                } else {
                    // Handle the case where the recipe object was not found (shouldn't happen if your data is consistent)
                    Toast.makeText(Search.this, "Could not find recipe details.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.upload_recipe) {
                Intent i = new Intent(Search.this, UploadRecipeActivity.class);
                Search.this.startActivity(i);
                return true;
            }
            return false;
        });
    }

    private void updateListView(List<Recipe> recipes){
        List<String> recipeNames = new ArrayList<>();
        for(Recipe recipe: recipes){
            recipeNames.add(recipe.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Search.this, android.R.layout.simple_list_item_1, recipeNames);
        recipeListView.setAdapter(adapter);
    }
}