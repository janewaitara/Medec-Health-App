package com.example.medec;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Landing extends AppCompatActivity {

   ArrayList<DoctorDetails> mDoctorDetails;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mchildEventListener;

    private RecyclerView rvDoctors;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

      /*  FirebaseUtil.openFbReference("doctorsDetails");

        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference =FirebaseUtil.mDatabaseReference;
        mchildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                TextView tvDetails = (TextView) findViewById(R.id.tv_details);
                DoctorDetails dD = dataSnapshot.getValue(DoctorDetails.class);
                tvDetails.setText(tvDetails.getText() + "\n" + dD.getName());
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
   */


      rvDoctors = (RecyclerView) findViewById(R.id.rv_details);
      final DoctorsAdapter doctorDetailsAdapter = new DoctorsAdapter();
      rvDoctors.setAdapter(doctorDetailsAdapter);


      LinearLayoutManager doctorsLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
      rvDoctors.setLayoutManager(doctorsLayoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.doctorsprofile,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.docProfile:
                Intent intent = new Intent(Landing.this, DoctorsProfileActivity.class);
                startActivity(intent);
        }
        return true;
    }
}
