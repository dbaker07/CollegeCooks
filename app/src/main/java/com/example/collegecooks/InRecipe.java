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
    private TextView durationTextView;
    private TextView directionsTextView;
    //private ImageView recipeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_recipe);
        //initialize the textViews and buttons
        recipeNameTextView = findViewById(R.id.recipeNameTextView);
        durationTextView = findViewById(R.id.durationTextView);
        ingredientsTextView = findViewById(R.id.ingredientsTextView);
        directionsTextView = findViewById(R.id.directionsTextView);
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        //Gets the recipe to be displayed (passed from the search page
        String recipeKey = getIntent().getStringExtra("recipe_name");
        //pulls the recipe from firebase
        assert recipeKey != null;
        DatabaseReference recipeRef = FirebaseDatabase.getInstance()
                .getReference("RecipeList").child(recipeKey);

        recipeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Recipe recipe = snapshot.getValue(Recipe.class);
                    //creates the format that the recipe will be displayed in (checks to make sure the recipe isn't null before displaying)
                if (recipe != null) {
                    recipeNameTextView.setText(recipe.getName());
                    durationTextView.setText(recipe.getDuration());

                    ArrayList<Ingredient> ingredients = recipe.getIngredients();
                    Log.d("InRecipe", "Loaded ingredients: " + ingredients);
                    //checks to make sure the ingredients list isn't empty
                    if (recipe.getIngredients() != null && !recipe.getIngredients().isEmpty()) {
                        StringBuilder ingredientsBuilder = new StringBuilder();
                        //builds the bulleted list of ingredients to be displayed to the user
                        for (Ingredient ing : recipe.getIngredients()) {
                            ingredientsBuilder
                                    .append("• ")
                                    .append(ing.getAmt()).append(" ")
                                    .append(ing.getUnit()).append(" ")
                                    .append(ing.getInfo()).append("\n");
                        }
                        //sets the xml text to the list of ingredients
                        ingredientsTextView.setText(ingredientsBuilder.toString());
                    }
                    // if the ingredientList is empty, the text displayed is "no ingredients found" (which shouldn't happen if the recipe was uploaded correctly)
                    else {
                        String nullValue = "No ingredients found.";
                        ingredientsTextView.setText(nullValue);
                    }
                    //sets the directions text
                    directionsTextView.setText(recipe.getDirections());


                    } else {
                        Toast.makeText(InRecipe.this, "Recipe not found", Toast.LENGTH_SHORT).show();
                    }
                }
                //if recipe isn't able to be loaded for one reason or another...
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(InRecipe.this, "Error loading recipe", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

