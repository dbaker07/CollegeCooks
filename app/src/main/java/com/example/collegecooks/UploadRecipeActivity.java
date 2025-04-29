package com.example.collegecooks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UploadRecipeActivity extends AppCompatActivity {
        //Inititalize all variables
    private EditText recipeNameEditText;
    private EditText amt1EditText;
    private EditText unit1EditText;
    private EditText ingredient1EditText;
    private EditText amt2EditText;
    private EditText unit2EditText;
    private EditText ingredient2EditText;
    private EditText amt3EditText;
    private EditText unit3EditText;
    private EditText ingredient3EditText;
    private EditText amt4EditText;
    private EditText unit4EditText;
    private EditText ingredient4EditText;
    private EditText amt5EditText;
    private EditText unit5EditText;
    private EditText ingredient5EditText;
    private EditText directionsEditText;
    private EditText durationEditText;
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
        //Converts each xml object into a java object
        recipeNameEditText = findViewById(R.id.recipeNameEditText);
        durationEditText = findViewById(R.id.durationEditText);
        amt1EditText = findViewById(R.id.amt1EditText);
        unit1EditText = findViewById(R.id.unit1EditText);
        ingredient1EditText = findViewById(R.id.ingredient1EditText);
        amt2EditText = findViewById(R.id.amt2EditText);
        unit2EditText = findViewById(R.id.unit2EditText);
        ingredient2EditText = findViewById(R.id.ingredient2EditText);
        amt3EditText = findViewById(R.id.amt3EditText);
        unit3EditText = findViewById(R.id.unit3EditText);
        ingredient3EditText = findViewById(R.id.ingredient3EditText);
        amt4EditText = findViewById(R.id.amt4EditText);
        unit4EditText = findViewById(R.id.unit4EditText);
        ingredient4EditText = findViewById(R.id.ingredient4EditText);
        amt5EditText = findViewById(R.id.amt5EditText);
        unit5EditText = findViewById(R.id.unit5EditText);
        ingredient5EditText = findViewById(R.id.ingredient5EditText);
        directionsEditText = findViewById(R.id.DirectionsEditText);
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

    /**
     * Uploads the recipe into Firebase
     */
    private void uploadRecipe() {
        //makes sure all required fields are filled in, does not allow user to upload otherwise
        if (!validateForm()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        //constructs the components of a recipe object
        String recipeName = recipeNameEditText.getText().toString();
        ArrayList <Ingredient> ingredients = makeIngredientList();
        String directions = directionsEditText.getText().toString();
        String duration = durationEditText.getText().toString();
        //creates the recipe object
        Recipe recipe = new Recipe(recipeName, duration, ingredients, directions );
        //Logs the firebase upload and pushes the recipe components to firebase
        Log.d("UploadRecipeActivity", "Creating firebase instance");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.d("UploadRecipeActivity", "Creating the reference");
        DatabaseReference newref = database.getReference("RecipeList");
        Log.d("UploadRecipeActivity" , "Setting reference value");
        newref.child(recipeName).setValue(recipe)
                .addOnSuccessListener(aVoid -> {
                    clearForm();
                    Toast.makeText(this, "Recipe Uploaded Successfully!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to upload recipe: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });


    }

    /**
     * Makes the list of ingredients
     * @return will return an arrayList of ingredients
     */
    public ArrayList <Ingredient> makeIngredientList() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();

        //first three ingredients are required and already validated in main code
        Ingredient ing1 = createIngredient(amt1EditText,unit1EditText,ingredient1EditText);
        ingredients.add(ing1);
        Ingredient ing2 = createIngredient(amt2EditText,unit2EditText,ingredient2EditText);
        ingredients.add(ing2);
        Ingredient ing3 = createIngredient(amt3EditText,unit3EditText,ingredient3EditText);
        ingredients.add(ing3);
        //check if ingredient 4 can be added
        Ingredient ing4 = createIngredient(amt4EditText,unit4EditText,ingredient4EditText);
        if(ing4 != null) {ingredients.add(ing4);}
        //check if ingredient 5 can be added
        Ingredient ing5 = createIngredient(amt5EditText,unit5EditText,ingredient5EditText);
        if(ing5 != null) {ingredients.add(ing5);}
        return ingredients;
    }

    /**
     * Validates if the required components of a recipe are all filled out (name of recipe, duration
     * that it takes to make the recipe, directions, first three ingredients)
     * @return will return true if all fields are filled, will return false otherwise
     */
    private boolean validateForm() {
        //checks if recipeName is emppty
        if (recipeNameEditText.getText().toString().isEmpty()) return false;
        //checks if duration is empty
        if (durationEditText.getText().toString().isEmpty()) return false;
        //checks if directions are empty
        if (directionsEditText.getText().toString().isEmpty()) return false;

        // Check at least the first 3 ingredients are fully filled
        return !(amt1EditText.getText().toString().isEmpty() ||
                unit1EditText.getText().toString().isEmpty() ||
                ingredient1EditText.getText().toString().isEmpty() ||

                amt2EditText.getText().toString().isEmpty() ||
                unit2EditText.getText().toString().isEmpty() ||
                ingredient2EditText.getText().toString().isEmpty() ||

                amt3EditText.getText().toString().isEmpty() ||
                unit3EditText.getText().toString().isEmpty() ||
                ingredient3EditText.getText().toString().isEmpty());
    }

    /**
     * Creates a new ingredient object, takes in the EditText values and converts them to strings
     * @param amtEditText is the EdiText of the amount
     * @param unitEditText is the EditText that takes in the unit
     * @param infoEditText is the EditText that takes the directions
     * @return will return an new ingredient object
     */
    private Ingredient createIngredient(EditText amtEditText, EditText unitEditText, EditText infoEditText) {
        String amtStr = amtEditText.getText().toString().trim();
        String unit = unitEditText.getText().toString().trim();
        String info = infoEditText.getText().toString().trim();
        //nulls an ingredient if one or more fields is empty
        if (amtStr.isEmpty() || unit.isEmpty() || info.isEmpty()) {
            return null;
        }
        //sends an error if the user enters a string instead of a double (prevents crashing)
        try {
            double amount = Double.parseDouble(amtStr);
            return new Ingredient(amount, unit, info);
        } catch (NumberFormatException e) {
            Log.e("UploadRecipeActivity", "Invalid amount: " + amtStr);
            return null;
        }
    }
    public void clearForm() {
        recipeNameEditText.setText("");
        durationEditText.setText("");

        amt1EditText.setText("");
        unit1EditText.setText("");
        ingredient1EditText.setText("");

        amt2EditText.setText("");
        unit2EditText.setText("");
        ingredient2EditText.setText("");

        amt3EditText.setText("");
        unit3EditText.setText("");
        ingredient3EditText.setText("");

        amt4EditText.setText("");
        unit4EditText.setText("");
        ingredient4EditText.setText("");

        amt5EditText.setText("");
        unit5EditText.setText("");
        ingredient5EditText.setText("");

        directionsEditText.setText("");
        recipeImageView.setImageResource(R.color.gray); // Reset the imageView
    }
}