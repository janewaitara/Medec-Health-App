package com.example.medec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {

    SearchView mSearchView;
    RecyclerView mCountyRecycler;

    private List<Object> mRecyclerViewItems ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        mSearchView = findViewById(R.id.searchView);
        mRecyclerViewItems = new ArrayList<>();

        initializeDisplayContent();

    }

    private void initializeDisplayContent() {

        mCountyRecycler = findViewById(R.id.county_recyclerView);

        LinearLayoutManager countiesLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        mCountyRecycler.setLayoutManager(countiesLayoutManager);

        LocationRecyclerAdapter locationRecyclerAdapter = new LocationRecyclerAdapter(this);
        mCountyRecycler.setAdapter(locationRecyclerAdapter);


    }

    public String readJsonDataFromFile() throws IOException{

        InputStream inputStream =null;
        StringBuilder builder = new StringBuilder();

        try {

            String jsonDataString = null;
            inputStream = getResources().openRawResource(R.raw.counties);
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(inputStream, "UTF-8"));
            while((jsonDataString = bufferedReader.readLine()) != null){
                builder.append(jsonDataString);
            }
        }finally {
            if (inputStream != null){
                inputStream.close();
            }

        }

        return new String(builder);

    }

}
