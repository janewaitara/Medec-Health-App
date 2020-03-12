package com.example.medec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PatientLandingPage extends AppCompatActivity {

   ArrayList<DoctorDetails> mDoctorDetails;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mchildEventListener;

    private RecyclerView rvDoctors;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_landing);

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



       BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
       bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               switch (item.getItemId()) {
                   case R.id.navigation_home:
                       startActivity(new Intent(PatientLandingPage.this, PatientLandingPage.class));
                       break;
                   case R.id.navigation_chat:
                       Toast.makeText(PatientLandingPage.this, "Favorites", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.navigation_account:
                       startActivity(new Intent(PatientLandingPage.this, PatientAccountActivity.class));
                       break;
               }
               return true;
           }
       });

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
                Intent intent = new Intent(PatientLandingPage.this, DoctorsProfileActivity.class);
                startActivity(intent);
        }
        return true;
    }
}
