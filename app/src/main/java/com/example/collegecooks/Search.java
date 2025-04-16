package com.example.collegecooks;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SearchView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);



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
        if (item.getItemId() == R.id.action_settings) {
            Intent i = new Intent(Search.this, UploadRecipeActivity.class);
            Search.this.startActivity(i);
            return true;
        }
        if (item.getItemId() == R.id.action_favorite) {
            Intent i = new Intent(Search.this, UploadRecipeActivity.class);
            Search.this.startActivity(i);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);

        }
    }
}
