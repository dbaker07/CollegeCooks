package com.example.collegecooks;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SearchView;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);

        BottomNavigationView bottomMenu = findViewById(R.id.bottomNavigationView);

        bottomMenu.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.upload_recipe) {
                Intent i = new Intent(Search.this, UploadRecipeActivity.class);
                Search.this.startActivity(i);
                return true;
            }
            return true;
        });

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

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.upload_recipe) {
            Intent i = new Intent(Search.this, UploadRecipeActivity.class);
            Search.this.startActivity(i);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);

        }
    }
}
