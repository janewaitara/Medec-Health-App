package com.example.medec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.SearchView;

public class LocationActivity extends AppCompatActivity {

    SearchView mSearchView;
    RecyclerView mCountyRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        mSearchView = findViewById(R.id.searchView);

        initializeDisplayContent();

    }

    private void initializeDisplayContent() {

        mCountyRecycler = findViewById(R.id.county_recyclerView);

        LinearLayoutManager countiesLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        mCountyRecycler.setLayoutManager(countiesLayoutManager);
    }
}
