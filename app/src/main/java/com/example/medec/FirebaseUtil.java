package com.example.medec;

import com.example.medec.Doctors.DoctorDetails;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseUtil {

    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;

    private static FirebaseUtil firebaseUtil;
    public static ArrayList<DoctorDetails> mDoctorDetails;

    private FirebaseUtil() {
    }

    public static void openFbReference(String ref) { //open reference of child passsed a parameter
        if (firebaseUtil == null) {
            firebaseUtil = new FirebaseUtil();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mDoctorDetails = new ArrayList<DoctorDetails>();
        }
        mDatabaseReference = mFirebaseDatabase.getReference().child(ref);

    }
}
