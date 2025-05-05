package com.example.collegecooks;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class Search extends AppCompatActivity {
    private RecipeRetriever recipeRetriever;
    private List<Recipe> allRecipes;
    private ListView recipeListView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allRecipes = new ArrayList<>();
        recipeListView = findViewById(R.id.recipeListView);
        recipeRetriever = new RecipeRetriever();
        setContentView(R.layout.search_page);
        SearchView searchbar = findViewById(R.id.search);
        RecipeRetriever.retrieveAllRecipes();

        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                List<Recipe> filteredRecipes = recipeRetriever.searchRecipes(allRecipes, query);
                updateListView(filteredRecipes);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
            private void updateListView(List<Recipe> recipes){
                List<String> recipeNames = new ArrayList<>();
                for(Recipe recipe: recipes){
                    recipeNames.add(recipe.getName());
                }
                adapter = new ArrayAdapter<>(Search.this, android.R.layout.simple_list_item_1, recipeNames);
                recipeListView.setAdapter(adapter);
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

}
