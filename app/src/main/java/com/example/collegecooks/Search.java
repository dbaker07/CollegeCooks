package com.example.collegecooks;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);
        SearchView searchbar = findViewById(R.id.search);

        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent i = new Intent(Search.this, UploadRecipeActivity.class);
                Search.this.startActivity(i);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
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
