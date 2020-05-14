package com.example.medec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    public static final int PASSWORDLENGTH = 6;
    private TextInputLayout userRolesTextInputLayout;
    private TextInputLayout loginEmailTextInputLayout;
    private TextInputLayout loginPasswordTextInputLayout;
    private AutoCompleteTextView userRolesDropDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userRolesDropDown = findViewById(R.id.login_dropdown_userRoles);

        //an array for the users' roles
        String[] userRoles = new String[]{
                "Doctor",
                "Patient"
        };

        ArrayAdapter<String>  userRolesAdapter = new ArrayAdapter<>(
                LoginActivity.this, R.layout.dropdownmenu_userroles, userRoles
        );

        userRolesDropDown.setAdapter(userRolesAdapter);


        userRolesTextInputLayout = findViewById(R.id.login_userRolesWrapper);
        loginEmailTextInputLayout = findViewById(R.id.login_emailWrapper);
        loginPasswordTextInputLayout = findViewById(R.id.login_passwordWrapper);
    }


}
