package com.example.medec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {


    RecyclerView mCountyRecycler;
    EditText searchView;

    private List<CountyDetails> mRecyclerViewItems ;
    private LocationRecyclerAdapter locationRecyclerAdapter;
    //Button btnCounty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);


       /* btnCounty = findViewById(R.id.btncounty);
        btnCounty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMenuItemsFromJson();
            }
        });*/

        mRecyclerViewItems = new ArrayList<>();
        searchView = findViewById(R.id.searchView);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());

            }
        });

        initializeDisplayContent();

        addMenuItemsFromJson();

        Log.d("TAG", "RecyclerView Data:" + mRecyclerViewItems.toString() + "\n");

    }

    private void filter(String text) {

        ArrayList<CountyDetails> filteredList = new ArrayList<>();

        for (CountyDetails item : mRecyclerViewItems){
            if (item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }

        locationRecyclerAdapter.filterList(filteredList);

    }

    private void initializeDisplayContent() {

        mCountyRecycler = findViewById(R.id.county_recyclerView);

        LinearLayoutManager countiesLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        mCountyRecycler.setLayoutManager(countiesLayoutManager);

        locationRecyclerAdapter = new LocationRecyclerAdapter(this, mRecyclerViewItems);
        mCountyRecycler.setAdapter(locationRecyclerAdapter);


    }

    public void addMenuItemsFromJson(){

        StringBuilder builder = new StringBuilder();
        try {
            String jsonDataString = readJsonDataFromFile();
           /* TextView textView = findViewById(R.id.country);
            textView.setText(jsonDataString);*/ //confirming whether data has been successfully read
            JSONArray menuItemsJsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < menuItemsJsonArray.length(); i++){

                JSONObject menuItemObject = menuItemsJsonArray.getJSONObject(i);
                String menuItemsName = menuItemObject.getString("name");
                int menuItemsCode = menuItemObject.getInt("code");
                String menuItemsCapital = menuItemObject.getString("capital");

              /*  //confirming whether all items are being read

                Log.d("TAG", menuItemsName);

                builder.append(menuItemsCode).append(": ").append(menuItemsName).append("\n");

                TextView textView = findViewById(R.id.country);
                textView.setText(builder);*/


                CountyDetails countyDetails = new CountyDetails(menuItemsName, menuItemsCode, menuItemsCapital);
                mRecyclerViewItems.add(countyDetails);
            }

        } catch (IOException | JSONException exception) {
            Log.e(LocationActivity.class.getName(), "Unable to parse the JSON file.",exception);
        }
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
