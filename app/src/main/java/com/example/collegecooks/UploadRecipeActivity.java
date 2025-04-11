package com.example.collegecooks;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UploadRecipeActivity extends AppCompatActivity {

    private EditText recipeNameEditText;
    private EditText ingredientsEditText;
    private EditText instructionsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_recipe);

        recipeNameEditText = findViewById(R.id.recipeNameEditText);
        ingredientsEditText = findViewById(R.id.ingredientsEditText);
        instructionsEditText = findViewById(R.id.instructionsEditText);
        Button uploadButton = findViewById(R.id.uploadButton);

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadRecipe();
            }
        });
    }

    private void uploadRecipe() {
        String recipeName = recipeNameEditText.getText().toString();
        String ingredients = ingredientsEditText.getText().toString();
        String instructions = instructionsEditText.getText().toString();


        if (recipeName.isEmpty() || ingredients.isEmpty() || instructions.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }


        saveRecipe(recipeName, ingredients, instructions);
        Toast.makeText(this, "Recipe Uploaded: " + recipeName, Toast.LENGTH_SHORT).show();


        recipeNameEditText.setText("");
        ingredientsEditText.setText("");
        instructionsEditText.setText("");
    }

    private void saveRecipe(String recipeName, String ingredients, String instructions) {
        System.out.println("Recipe Name: " + recipeName);
        System.out.println("Ingredients: " + ingredients);
        System.out.println("Instructions: " + instructions);
    }
}