package com.example.medec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public FirebaseAuth mFirebaseAuth;
    public static final int PASSWORDLENGTH = 6;
    private TextInputLayout loginEmailTextInputLayout;
    private TextInputLayout loginPasswordTextInputLayout;
    private Spinner userRolesSpinner;

    private AutoCompleteTextView userRolesDropDown;
    TextView registerText, forgotPassword;
    ProgressBar loginProgressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmailTextInputLayout = findViewById(R.id.login_emailWrapper);
        loginPasswordTextInputLayout = findViewById(R.id.login_passwordWrapper);
        loginProgressBar = findViewById(R.id.login_progress_bar);
        registerText = findViewById(R.id.login_register);
        forgotPassword = findViewById(R.id.forgot_password);
        mFirebaseAuth = FirebaseAuth.getInstance();


        userRolesSpinner = findViewById(R.id.login_UserRoles_spinner);
        ArrayAdapter<CharSequence> userRolesSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.userRoles, android.R.layout.simple_spinner_item);
        userRolesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userRolesSpinner.setAdapter(userRolesSpinnerAdapter);
        userRolesSpinner.setOnItemSelectedListener(this);


        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });

        /**using autocompleteTextView
         * userRolesDropDown = findViewById(R.id.login_dropdown_userRoles);

            //an array for the users' roles from strings.xml
            String[] userRoles = getResources().getStringArray(R.array.userRoles);

            ArrayAdapter<String>  userRolesAdapter = new ArrayAdapter<String>(
                    LoginActivity.this, R.layout.dropdownmenu_userroles, userRoles
            );
            userRolesDropDown.setAdapter(userRolesAdapter);*/


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String loginUserRole = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public boolean validatePassword(){
        String loginPassword = loginPasswordTextInputLayout.getEditText().getText().toString().trim();

        if (loginPassword.isEmpty()){
            loginPasswordTextInputLayout.setError("Password can not be empty");
            return false;
        }else if (loginPassword.length()< PASSWORDLENGTH){
            loginPasswordTextInputLayout.setError("password should be greater than 6");
            return false;
        }else {
            loginPasswordTextInputLayout.setError(null);
            // loginEmailTextInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    public boolean validateEmail(){
        String loginEmail = loginEmailTextInputLayout.getEditText().getText().toString().trim();

        if (loginEmail.isEmpty()){
            loginEmailTextInputLayout.setError("Email address can not be empty");
            return false;
        }else {
            loginEmailTextInputLayout.setError(null);
            // loginEmailTextInputLayout.setErrorEnabled(false);
            return true;
        }
    }


    public void loginUser(View view) {

        String loginEmail = loginEmailTextInputLayout.getEditText().getText().toString().trim();
        String loginPassword = loginPasswordTextInputLayout.getEditText().getText().toString().trim();
        if(!validateEmail()|!validatePassword()){
            return;
        }

        String input = "Email: " + loginEmailTextInputLayout.getEditText().getText().toString();
        input += "\n ";
        input += "Password: " + loginPasswordTextInputLayout.getEditText().getText().toString();
        input += "\n ";
        input += "UserRole: " + userRolesSpinner.getSelectedItem().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();


        loginProgressBar.setVisibility(View.VISIBLE);
        //authenticate the user

        mFirebaseAuth.signInWithEmailAndPassword(loginEmail,loginPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                    if (userRolesSpinner.getSelectedItem().toString().equals("Doctor")){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        loginProgressBar.setVisibility(View.GONE);
                    }

                }else{
                    Toast.makeText(LoginActivity.this, "Login failed." + task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                    loginProgressBar.setVisibility(View.GONE);
                }
            }
        });

    }

    public void resetPassword(View view) {

        final EditText resetPasswordEmail = new EditText(view.getContext());

        AlertDialog.Builder passwordResetDialog =new AlertDialog.Builder(view.getContext());
        passwordResetDialog.setTitle("Reset Password");
        passwordResetDialog.setMessage("Enter your email to receive Reset Link");
        passwordResetDialog.setView(resetPasswordEmail);
        passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //extract the email and send reset link
                String email = resetPasswordEmail.getText().toString().trim();
                mFirebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(LoginActivity.this, "Reset Link Sent Your Email", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Error! Reset Link not sent " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //close the dialog
            }
        });
        passwordResetDialog.create().show();
    }
}
