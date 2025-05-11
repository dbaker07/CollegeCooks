package com.example.collegecooks;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InRecipe extends AppCompatActivity {

    private TextView recipeNameTextView;
    private TextView ingredientsTextView;
    private TextView directionsTextView;
    //private ImageView recipeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_recipe);

        recipeNameTextView = findViewById(R.id.recipeNameTextView);
        ingredientsTextView = findViewById(R.id.ingredientsTextView);
        directionsTextView = findViewById(R.id.directionsTextView);
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        //recipeImageView = findViewById(R.id.recipeImageView);

        // Get the recipe key (name or Firebase ID passed from previous screen)
        //String recipeKey = getIntent().getStringExtra("A");
        String recipeKey = getIntent().getStringExtra("recipe_name");

        DatabaseReference recipeRef = FirebaseDatabase.getInstance()
                .getReference("RecipeList").child(recipeKey);

        recipeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Recipe recipe = snapshot.getValue(Recipe.class);

                if (recipe != null) {
                    recipeNameTextView.setText(recipe.getName());
                    ArrayList<Ingredient> ingredients = recipe.getIngredients();
                    Log.d("InRecipe", "Loaded ingredients: " + ingredients);

                    if (recipe.getIngredients() != null && !recipe.getIngredients().isEmpty()) {
                        StringBuilder ingredientsBuilder = new StringBuilder();
                        for (Ingredient ing : recipe.getIngredients()) {
                            ingredientsBuilder
                                    .append("• ")
                                    .append(ing.getAmt()).append(" ")
                                    .append(ing.getUnit()).append(" ")
                                    .append(ing.getInfo()).append("\n");
                        }
                        ingredientsTextView.setText(ingredientsBuilder.toString());
                    } else {
                        String nullValue = "No ingredients found.";
                        ingredientsTextView.setText(nullValue);
                    }
                    //ingredientsTextView.setText(ingredientsBuilder.toString());

                    directionsTextView.setText(recipe.getDirections());

                    // Display image using Glide or Picasso
                   /* if (recipe.getImageUrl() != null && !recipe.getImageUrl().isEmpty()) {
                        Glide.with(InRecipe.this)
                                .load(recipe.getImageUrl())
                                .placeholder(R.drawable.ic_placeholder)
                                .into(recipeImageView);
                    }*/
                    } else {
                        Toast.makeText(InRecipe.this, "Recipe not found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(InRecipe.this, "Error loading recipe", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

