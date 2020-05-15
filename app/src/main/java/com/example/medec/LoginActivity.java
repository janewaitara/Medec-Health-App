package com.example.medec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final int PASSWORDLENGTH = 6;
    private TextInputLayout loginEmailTextInputLayout;
    private TextInputLayout loginPasswordTextInputLayout;
    private Spinner userRolesSpinner;

    private AutoCompleteTextView userRolesDropDown;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmailTextInputLayout = findViewById(R.id.login_emailWrapper);
        loginPasswordTextInputLayout = findViewById(R.id.login_passwordWrapper);


        userRolesSpinner = findViewById(R.id.login_UserRoles_spinner);
        ArrayAdapter<CharSequence> userRolesSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.userRoles, android.R.layout.simple_spinner_item);
        userRolesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userRolesSpinner.setAdapter(userRolesSpinnerAdapter);
        userRolesSpinner.setOnItemSelectedListener(this);


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
        if(!validateEmail()|!validatePassword()){
            return;
        }

        String input = "Email: " + loginEmailTextInputLayout.getEditText().getText().toString();
        input += "\n ";
        input += "Password: " + loginPasswordTextInputLayout.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();

        if (userRolesSpinner.getSelectedItem().toString().equals("Doctor")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }


}
