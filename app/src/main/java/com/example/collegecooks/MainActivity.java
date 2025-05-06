package com.example.collegecooks;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Log.d("MainActivity", "Creating firebase instance");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.d("MainActivity", "Creating the reference");
        DatabaseReference newref = database.getReference("messsage");
        Log.d("MainActivity" , "Setting reference value");
        newref.child("recipe1").setValue("Hello");
        Log.d("MainActivity" , "Adding the value event listener");
        newref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("MainActivity" , "Setting the string value to the reference value");
                Recipe value = snapshot.getValue(Recipe.class);
                Log.d("FirebaseTest", "Value is:" + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("MainActivity" , "Logging an error");
                Log.w("FirebaseTest", "Failed to read value", error.toException());
            }
        });

        //return false;
    }
}