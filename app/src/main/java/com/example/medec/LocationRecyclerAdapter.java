package com.example.medec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LocationRecyclerAdapter extends RecyclerView.Adapter<LocationRecyclerAdapter.LocationViewHolder> {

    private final Context mcontext;
    private final LayoutInflater mLayoutInflater;

    public LocationRecyclerAdapter(Context mcontext) {
        this.mcontext = mcontext;
        mLayoutInflater = LayoutInflater.from(mcontext);
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

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {

        public final TextView textCounty;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            //reference to the textView
            textCounty = itemView.findViewById(R.id.text_county);
        }
    }

}
