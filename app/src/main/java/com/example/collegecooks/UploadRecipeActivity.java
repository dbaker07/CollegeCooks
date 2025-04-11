package com.example.collegecooks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class UploadRecipeActivity extends AppCompatActivity {

    private EditText recipeNameEditText;
    private EditText ingredientsEditText;
    private EditText instructionsEditText;
    private ImageView recipeImageView;

    private final ActivityResultLauncher<Intent> imagePickerLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Intent data = result.getData();
                            Uri selectedImageUri = data.getData();
                            recipeImageView.setImageURI(selectedImageUri);
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_recipe);

        recipeNameEditText = findViewById(R.id.recipeNameEditText);
        ingredientsEditText = findViewById(R.id.ingredientsEditText);
        instructionsEditText = findViewById(R.id.instructionsEditText);
        // Initialize the new views
        recipeImageView = findViewById(R.id.recipeImageView);
        Button selectImageButton = findViewById(R.id.selectImageButton);
        Button uploadButton = findViewById(R.id.uploadButton);

        // Set click listener for the select image button
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadRecipe();
            }
        });
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imagePickerLauncher.launch(intent);
    }

    private void uploadRecipe() {
        String recipeName = recipeNameEditText.getText().toString();
        String ingredients = ingredientsEditText.getText().toString();
        String instructions = instructionsEditText.getText().toString();

        if (recipeName.isEmpty() || ingredients.isEmpty() || instructions.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }


        String imageString = "";

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