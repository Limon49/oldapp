package com.login_signup_screendesign_demo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class signupforrenter extends AppCompatActivity implements View.OnClickListener {
    private static View view;
    private static EditText fullName, emailId, mobileNumber, location,
            password, confirmPassword;
    private static TextView login;
    private static Button signUpButton,pay;
    private static CheckBox terms_conditions;
    String namepattern ="[a-zA-z0-9]+";
    String emailpattern ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
    String mobilepattern ="[0-9]{11}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupforrenter);
        initViews();
        setListeners();
        return ;

    }

    private void initViews() {
        fullName = findViewById(R.id.fullName);
        emailId = findViewById(R.id.userEmailId);
        mobileNumber = findViewById(R.id.mobileNumber);
        location = findViewById(R.id.location);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        pay = findViewById(R.id.payment);
        signUpButton =  findViewById(R.id.signUpBtn);
        login =  findViewById(R.id.already_user);
        terms_conditions =  findViewById(R.id.terms_conditions);
    }


    private void setListeners() {
        signUpButton.setOnClickListener((View.OnClickListener) this);
        login.setOnClickListener((View.OnClickListener) this);
        pay.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.signUpBtn:
                checkvalidation();
                break;

            case R.id.already_user:
                Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                break;

            case R.id.payment:
                break;
        }
    }



    private void checkvalidation() {
        String fullname = fullName.getText().toString();
        String email = emailId.getText().toString();
        String mobile = mobileNumber.getText().toString();
        String locat = location.getText().toString();
        String pass = password.getText().toString();
        String conf = confirmPassword.getText().toString();



        if (fullname.isEmpty()) {
            fullName.setError("Enter your name");
            fullName.requestFocus();
        }
        if (!fullName.getText().toString().matches(namepattern) && fullName.length() < 7) {
            fullName.setError("Enter 7 word valid name");
            fullName.requestFocus();
        }


        if (email.isEmpty()) {
            emailId.setError("Enter your emailid");
            emailId.requestFocus();
        }
        if (!emailId.getText().toString().matches(emailpattern)) {
            emailId.setError("Enter valid emailId");
            emailId.requestFocus();
        }
        if (mobile.isEmpty()) {
            mobileNumber.setError("Enter mobile number");
            mobileNumber.requestFocus();
        }
        if (!mobileNumber.getText().toString().matches(mobilepattern)) {
            mobileNumber.setError("ENter 11 digit valid mobile number");
            mobileNumber.requestFocus();
        }

        if (locat.isEmpty()) {
            location.setError("Enter your location");
            location.requestFocus();
        }
        if (pass.isEmpty()) {
            password.setError("Enter your password");
            password.requestFocus();
        }
        if (!password.getText().toString().matches(PASSWORD_PATTERN) && password.length() < 8) {
            password.setError("Enter 8 digit valid password");

        }
        if (conf.isEmpty()) {
            confirmPassword.setError("Enter confirm password");
            confirmPassword.requestFocus();
        }


        if (!conf.equals(pass))
            confirmPassword.setError("Both password does not match");
        confirmPassword.requestFocus();



        if(!terms_conditions.isChecked())
        {
            terms_conditions.setError("You have not accepted our terms and conditions");
            terms_conditions.requestFocus();
        }



    }
}
