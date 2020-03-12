package com.example.medec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    public FirebaseAuth mFirebaseAuth;
    EditText fullName, registerEmail, registerPassword, registerPhone;
    Button registerButton;
    TextView registeredLogin;
    ProgressBar registerProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullName = (EditText) findViewById(R.id.register_fullname);
        registerEmail = (EditText) findViewById(R.id.register_email);
        registerPassword = (EditText) findViewById(R.id.register_password);
        registerPhone = (EditText) findViewById(R.id.register_phone);
        registerButton = (Button) findViewById(R.id.btn_register);
        registeredLogin = (TextView) findViewById(R.id.registered_login);


        mFirebaseAuth = FirebaseAuth.getInstance();
        registerProgressBar = findViewById(R.id.register_progress_bar);


        if (mFirebaseAuth.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mFullName = fullName.getText().toString();
                String mRegisterEmail = registerEmail.getText().toString();
                String mRegisterPassword = registerPassword.getText().toString();
                String mRegisterPhone = registerPhone.getText().toString();


                if (TextUtils.isEmpty(mRegisterEmail)) {
                    registerEmail.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(mRegisterPassword)) {
                    registerPassword.setError("Email is Required");
                    return;
                }
                if (mRegisterPassword.length() < 6) {
                    registerPassword.setError("Password must >= 6 characters");
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
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });


            }
        });
    }
}
