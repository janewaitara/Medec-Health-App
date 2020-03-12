package com.example.medec;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorsProfileActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    EditText txtDoctorName;
    EditText txtDoctorEmail;
    EditText txtDoctorContact;
    EditText txtDocCoursePursued;
    EditText txtDoctorTitle;
    EditText txtDocYrOfExperince;
    Button btnDoctorSaveProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_profile);

        FirebaseUtil.openFbReference("doctorsDetails");
        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference =FirebaseUtil.mDatabaseReference;

        txtDoctorName = (EditText) findViewById(R.id.edit_doctor_name);
        txtDoctorEmail = (EditText) findViewById(R.id.edit_doctor_email);
        txtDoctorContact = (EditText) findViewById(R.id.edit_doctor_contact);
        txtDocCoursePursued = (EditText) findViewById(R.id.edit_doctor_course);
        txtDoctorTitle = (EditText) findViewById(R.id.edit_doctor_title);
        txtDocYrOfExperince = (EditText) findViewById(R.id.edit_yearsofexperince);
        btnDoctorSaveProfile = (Button) findViewById(R.id.save_doctorProfile_button);

        btnDoctorSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDoctorProfile();
                Toast.makeText(DoctorsProfileActivity.this, "Profile saved", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveDoctorProfile() {
        String doctorName = txtDoctorName.getText().toString();
        String doctorEmail = txtDoctorEmail.getText().toString();
        String doctorContact = txtDoctorContact.getText().toString();
        String doctorCourse = txtDocCoursePursued.getText().toString();
        String doctorTitle = txtDoctorTitle.getText().toString();
        String yrOfExperience = txtDocYrOfExperince.getText().toString();

        DoctorDetails doctorDetails = new DoctorDetails(doctorName, doctorEmail,doctorContact,doctorCourse, doctorTitle,yrOfExperience, " ");
        mDatabaseReference.push().setValue(doctorDetails);

    }

}
