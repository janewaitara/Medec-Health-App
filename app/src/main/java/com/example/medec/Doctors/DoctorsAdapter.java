package com.example.medec.Doctors;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medec.FirebaseUtil;
import com.example.medec.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsAdapter.DoctorsViewHolder>{

    ArrayList<DoctorDetails> doctorDetails;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mchildEventListener;


    public DoctorsAdapter() {

        FirebaseUtil.openFbReference("doctorsDetails");
        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference =FirebaseUtil.mDatabaseReference;
        doctorDetails = FirebaseUtil.mDoctorDetails;
        mchildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DoctorDetails dD = dataSnapshot.getValue(DoctorDetails.class);
                Log.d("Details: ", dD.getName());
                dD.setId(dataSnapshot.getKey());
                doctorDetails.add(dD);//adding to the array the details passed
                notifyItemInserted(doctorDetails.size()-1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabaseReference.addChildEventListener(mchildEventListener);
    }


    @NonNull
    @Override
    public DoctorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.doctors_near_by, parent , false);
        return new DoctorsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorsViewHolder holder, int position)
    {
        DoctorDetails docdetails = doctorDetails.get(position);
        holder.bind(docdetails);
    }

    @Override
    public int getItemCount() {
        return doctorDetails.size();
    } //item on arraylist

    public class DoctorsViewHolder extends RecyclerView.ViewHolder {
        TextView txtDoctorName;
        TextView txtDoctorDescription;
//      TextView txtDoctorCourse;
        //ImageView imageDoctor;

        public DoctorsViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDoctorName =(TextView)itemView.findViewById(R.id.text_doctor_name);
//          txtDoctorCourse = (TextView) itemView.findViewById(R.id.text_doctor_course);
            txtDoctorDescription = (TextView)itemView.findViewById(R.id.text_doctor_description);
            //imageDoctor = (ImageView)itemView.findViewById(R.id.imageDoctor);
        }

        public void bind(DoctorDetails docDetail){
            txtDoctorName.setText(docDetail.getName());
            // txtDoctorCourse.setText(docDetail.getDocCoursePursued());
            txtDoctorDescription.setText(docDetail.getDocCoursePursued() + "," + docDetail.getDoctorsTitle());

          //  imageDoctor.setImageResource(R.drawable.nurse);
        }
    }

}
