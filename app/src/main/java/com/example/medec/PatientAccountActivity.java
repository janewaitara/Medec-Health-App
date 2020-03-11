/*
package com.example.medec;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientAccountActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mchildEventListener;
    Button btnEditPatientProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_account);

        btnEditPatientProfile = (Button) findViewById(R.id.button_edit_patientProfile);
        btnEditPatientProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientAccountActivity.this, PatientEditProfileActivity.class));
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        startActivity(new Intent(PatientAccountActivity.this, PatientLandingPage.class));
                        break;
                    case R.id.navigation_chat:
                        Toast.makeText(PatientAccountActivity.this, "Favorites", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_account:
                        startActivity(new Intent(PatientAccountActivity.this, PatientAccountActivity.class));
                        break;
                }
                return true;
            }
        });


        FirebaseUtil.openFbReference("patientDetails");
        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference =FirebaseUtil.mDatabaseReference;
        mchildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                TextView txtPatientName= (TextView)  findViewById(R.id.patient_name);
                TextView txtPatientContacts = (TextView) findViewById(R.id.patient_contacts);
                PatientDetails pD = dataSnapshot.getValue(PatientDetails.class); //populating the PatientDetails
                txtPatientName.setText(pD.getPatientName());
                txtPatientContacts.setText(pD.getPatientContact());

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
}
*/
