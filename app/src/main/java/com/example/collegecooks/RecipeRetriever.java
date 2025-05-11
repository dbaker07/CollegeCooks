package com.example.collegecooks;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecipeRetriever {

    public interface RecipeCallback {
        void onRecipesLoaded(List<Recipe> recipes);
        void onRecipesLoadFailed(String errorMessage);
    }

    private static RecipeCallback recipeCallback;

    public void setRecipeCallback(RecipeCallback callback) {
        recipeCallback = callback;
    }

    public void retrieveAllRecipes() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference recipeListRef = database.getReference("RecipeList");

        recipeListRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Recipe> allRecipes = new ArrayList<>();
                for (DataSnapshot recipeSnapshot : dataSnapshot.getChildren()) {
                    Recipe recipe = recipeSnapshot.getValue(Recipe.class);
                    if (recipe != null) {
                        allRecipes.add(recipe);
                    } else {
                        Log.e("RecipeRetriever", "Recipe is null: ");
                    }
                }
                if (recipeCallback != null) {
                    recipeCallback.onRecipesLoaded(allRecipes);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("RecipeRetriever", "Error retrieving recipes: " + databaseError.getMessage());
                if (recipeCallback != null) {
                    recipeCallback.onRecipesLoadFailed(databaseError.getMessage());
                }
            }
        });
    }

    public List<Recipe> searchRecipes(List<Recipe> allRecipes, String query) {
        List<Recipe> searchResults = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) {
            return allRecipes; // Return all recipes if the query is empty
        }
        String lowerCaseQuery = query.toLowerCase(Locale.getDefault());

        for (Recipe recipe : allRecipes) {
            // Check if the recipe name matches the keyword
            if (recipe.getName().toLowerCase(Locale.getDefault()).contains(lowerCaseQuery)) {
                searchResults.add(recipe);
                continue;
            }

            // Check if any ingredient name matches the keyword
            if (recipe.getIngredients() != null) {
                for (Ingredient ingredient : recipe.getIngredients()) {
                    if (ingredient.getInfo().toLowerCase(Locale.getDefault()).contains(lowerCaseQuery)) {
                        searchResults.add(recipe);
                        break; // Add the recipe only once if any ingredient matches
                    }
                }
            }
        }
        return searchResults;
    }
}