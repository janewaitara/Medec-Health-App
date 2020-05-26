package com.example.medec;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LocationRecyclerAdapter extends RecyclerView.Adapter<LocationRecyclerAdapter.LocationViewHolder> {

    private final Context mcontext;
    private final LayoutInflater mLayoutInflater;
    private List<CountyDetails> mCountyDetails;
    private List<CountyDetails> countyList;

    public LocationRecyclerAdapter(Context mcontext, List<CountyDetails> mCountyDetails) {
        this.mcontext = mcontext;
        mLayoutInflater = LayoutInflater.from(mcontext);

        this.mCountyDetails = mCountyDetails;
        this.countyList = new ArrayList<>(mCountyDetails);
    }


    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Context context = parent.getContext();
        View itemView = mLayoutInflater.inflate(R.layout.location_counties_recyclerview,parent,false);

        return new LocationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {

        CountyDetails county = (CountyDetails) mCountyDetails.get(position);
        holder.textCounty.setText(county.getName());


        //Log.d("TAG", county.getName());
    }

    @Override
    public int getItemCount() {
        return mCountyDetails.size();
    }



    public class LocationViewHolder extends RecyclerView.ViewHolder {

        public final TextView textCounty;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            //reference to the textView
            textCounty = itemView.findViewById(R.id.text_county);
        }

    }


    public void filterList(ArrayList<CountyDetails> filteredList){

        mCountyDetails = filteredList;
        notifyDataSetChanged();

    }



}
