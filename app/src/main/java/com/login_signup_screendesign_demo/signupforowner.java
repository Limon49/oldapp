package com.login_signup_screendesign_demo;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signupforowner extends AppCompatActivity  implements View.OnClickListener{

    private static View view;
    private static EditText fullName, emailid, mobileNumber, location,
            password, confirmPassword;
    private static TextView login;
    private static Button signUpButton,pay;
    private static CheckBox terms_conditions;
    private static ProgressBar progressbar;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;



    DatabaseReference reff;
    String namepattern ="[a-zA-z0-9 ]+";
    String emailpattern ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
    String mobilepattern ="[0-9]{11}";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupforowner);


        databaseReference = FirebaseDatabase.getInstance().getReference("Student");


        initViews();
        setListeners();





        return ;
    }


    private void initViews() {
        fullName = findViewById(R.id.fullName);
        emailid = findViewById(R.id.userEmailId);
        mobileNumber = findViewById(R.id.mobileNumber);
        location = findViewById(R.id.location);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        pay = findViewById(R.id.payment);
        signUpButton = findViewById(R.id.signUpBtn);
        login = findViewById(R.id.already_user);
        terms_conditions = findViewById(R.id.terms_conditions);
        progressbar = findViewById(R.id.progressbar);






    }





    private void setListeners() {
        signUpButton.setOnClickListener( this);
        login.setOnClickListener( this);
        pay.setOnClickListener( this);

    }





    private void checkvalidation() {
        String fullname = fullName.getText().toString();
        String emailId = emailid.getText().toString();
        String mobile = mobileNumber.getText().toString();
        String locat = location.getText().toString();
        String pass = password.getText().toString();
        String conf = confirmPassword.getText().toString();
        String paym =pay.getText().toString();
        String key =databaseReference.push().getKey();
        Student student =new Student(fullname,emailId,mobile, locat, pass,  conf);
        databaseReference.child(key).setValue(student);
        Toast.makeText(getApplicationContext(),"User info has been added",Toast.LENGTH_SHORT).show();



        if (fullname.isEmpty()) {
            fullName.setError("Enter your name");
            fullName.requestFocus();
        }
        if (!fullName.getText().toString().matches(namepattern) ) {
            fullName.setError("Enter  valid name");
            fullName.requestFocus();
        }


        if (emailId.isEmpty()) {
            emailid.setError("Enter your emailid");
            emailid.requestFocus();
        }

        if (!emailid.getText().toString().matches(emailpattern)) {
            emailid.setError("Enter valid emailId");
            emailid.requestFocus();
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

        progressbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(emailId, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressbar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            finish();
                            Intent intent =new Intent(getApplicationContext(),newactivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException)
                            {
                                Toast.makeText(getApplicationContext(),"You have already registered",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Error: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }

                        // ...
                    }
                });


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
}

