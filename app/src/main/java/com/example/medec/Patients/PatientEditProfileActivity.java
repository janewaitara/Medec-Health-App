package com.example.medec.Patients;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medec.FirebaseUtil;
import com.example.medec.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientEditProfileActivity extends AppCompatActivity {

private FirebaseDatabase mFirebaseDatabase;
private  DatabaseReference mDatabaseReference;

    EditText editPatientName;
    EditText editPatientEmail;
    EditText editPatientContact;
    Button btnPatientSaveProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_edit_profile);

        FirebaseUtil.openFbReference("patientDetails");
        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference =FirebaseUtil.mDatabaseReference;


        editPatientName = (EditText)findViewById(R.id.edit_patient_name);
        editPatientEmail = (EditText)findViewById(R.id.edit_patient_email);
        editPatientContact = (EditText)findViewById(R.id.edit_patient_contact);
        btnPatientSaveProfile = (Button) findViewById(R.id.save_patientProfile_button);
        btnPatientSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePatientProfile();
                Toast.makeText(PatientEditProfileActivity.this, "Profile saved", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void savePatientProfile() {
        
        String patientName = editPatientName.getText().toString();
        String patientEmailAddress = editPatientEmail.getText().toString();
        String patientContact = editPatientContact.getText().toString();
        
        PatientDetails patientDetails = new PatientDetails(patientName, patientEmailAddress,patientContact,"");
        mDatabaseReference.push().setValue(patientDetails);


    }
}
