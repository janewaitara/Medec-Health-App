package com.example.medec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import static com.example.medec.LoginActivity.PASSWORDLENGTH;

public class RegisterActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    public static final String TAG = "TAG";
    FirebaseFirestore mFirestore;
    public FirebaseAuth mFirebaseAuth;
    private TextInputLayout registerNameTextInputLayout;
    private TextInputLayout registerEmailTextInputLayout;
    private TextInputLayout registerPasswordTextInputLayout;
    private TextInputLayout registerPhoneTextInputLayout;

    EditText fullName, registerEmail, registerPassword, registerPhone;
    Spinner userRolesSpinner;
    Button registerButton;
    TextView registeredLogin;
    ProgressBar registerProgressBar;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        registerNameTextInputLayout = findViewById(R.id.register_nameWrapper);
        registerEmailTextInputLayout = findViewById(R.id.register_emailWrapper);
        registerPasswordTextInputLayout = findViewById(R.id.register_passwordWrapper);
        registerPhoneTextInputLayout = findViewById(R.id.register_phoneWrapper);

        fullName = (EditText) findViewById(R.id.register_fullname);
        registerEmail = (EditText) findViewById(R.id.register_email);
        registerPassword = (EditText) findViewById(R.id.register_password);
        registerPhone = (EditText) findViewById(R.id.register_phone);
        registerButton = (Button) findViewById(R.id.btn_register);
        registeredLogin = (TextView) findViewById(R.id.registered_login);
        registerProgressBar = findViewById(R.id.register_progress_bar);

        userRolesSpinner = findViewById(R.id.register_UserRoles_spinner);
        ArrayAdapter<CharSequence> userRolesSpinnerAdapter = ArrayAdapter.
                createFromResource(this,R.array.userRoles,android.R.layout.simple_spinner_item);
        userRolesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userRolesSpinner.setAdapter(userRolesSpinnerAdapter);
        userRolesSpinner.setOnItemSelectedListener(this);

        registeredLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });


        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirestore =  FirebaseFirestore.getInstance();


        if (mFirebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    public boolean validatePassword(){
        String loginPassword = registerPassword.getText().toString().trim();

        if (loginPassword.isEmpty()){
            registerPasswordTextInputLayout.setError("Password can not be empty");
            return false;
        }else if (loginPassword.length()< PASSWORDLENGTH){
            registerPasswordTextInputLayout.setError("password should be greater than 6");
            return false;
        }else {
            registerPasswordTextInputLayout.setError(null);
            // loginEmailTextInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    public boolean validateEmail(){
        String loginEmail = registerEmail.getText().toString().trim();

        if (loginEmail.isEmpty()){
            registerEmailTextInputLayout.setError("Email address can not be empty");
            return false;
        }else {
            registerEmailTextInputLayout.setError(null);
            // loginEmailTextInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    public void registerUser(View view) {
        final String mFullName = fullName.getText().toString();
        final String mRegisterEmail = registerEmail.getText().toString().trim();
        String mRegisterPassword = registerPassword.getText().toString().trim();
        final String mRegisterPhone = registerPhone.getText().toString();

        if(!validateEmail()|!validatePassword()){
            return;
        }

        registerProgressBar.setVisibility(View.VISIBLE);

        //register user

        mFirebaseAuth.createUserWithEmailAndPassword(mRegisterEmail, mRegisterPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            Toast.makeText(RegisterActivity.this, "User Created Successfully", Toast.LENGTH_SHORT).show();

                            userId = mFirebaseAuth.getCurrentUser().getUid();//getting the id of the current user

                            if (userRolesSpinner.getSelectedItem().toString().equals("Doctor")) {
                                addDoctorToFirestore(userId, mFullName, mRegisterEmail, mRegisterPhone);
                            }else  if (userRolesSpinner.getSelectedItem().toString().equals("Patient")) {
                                addPatientToFirestore(userId, mFullName, mRegisterEmail, mRegisterPhone);
                            }

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            registerProgressBar.setVisibility(View.GONE);


                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            registerProgressBar.setVisibility(View.GONE);

                        }
                    }
                });
    }

    private void addDoctorToFirestore(final String userId, String fullName, String email, String phone) {

        DocumentReference mDocumentReference = mFirestore.collection("doctors").document(userId);
        Map<String, Object> userData  = new HashMap<>();
        userData.put("fullName", fullName);
        userData.put("email", email);
        userData.put("phone", phone);
        mDocumentReference.set(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "User profile is created for: " + userId);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Error adding document" + e.toString());
            }
        });
    }

    private void addPatientToFirestore(final String userId, String fullName, String email, String phone) {

        DocumentReference mDocumentReference = mFirestore.collection("patients").document(userId);
        Map<String, Object> userData  = new HashMap<>();
        userData.put("fullName", fullName);
        userData.put("email", email);
        userData.put("phone", phone);
        mDocumentReference.set(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "User profile is created for: " + userId);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Error adding document" + e.toString());
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
