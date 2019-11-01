package com.login_signup_screendesign_demo;



import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

	private static EditText emailid, password;
	private static Button loginButton;
	private static TextView forgotPassword, signUp;
	private static CheckBox show_hide_password;

	private static LinearLayout loginLayout;
	private static ProgressBar progressbar;
	private FirebaseAuth mAuth;
	String emailpattern ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
	String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		mAuth = FirebaseAuth.getInstance();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		setListeners();

		return ;
	}


	private void initViews()
	{
		emailid =  findViewById(R.id.login_emailid);
		password =  findViewById(R.id.login_password);
		loginButton =  findViewById(R.id.loginBtn);
		forgotPassword =  findViewById(R.id.forgot_password);
		signUp =  findViewById(R.id.createAccount);
		show_hide_password =  findViewById(R.id.show_hide_password);
		loginLayout =  findViewById(R.id.login_layout);
		progressbar =findViewById(R.id.progressbar);
	}
	private void setListeners() {
		loginButton.setOnClickListener((View.OnClickListener) this);
		forgotPassword.setOnClickListener((View.OnClickListener) this);
		signUp.setOnClickListener((View.OnClickListener) this);

		show_hide_password
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


					public void onCheckedChanged(CompoundButton button,
												 boolean isChecked) {

						// If it is checkec then show password else hide
						// password
						if (isChecked) {

							show_hide_password.setText(R.string.hide_pwd);// change
							// checkbox
							// text

							password.setInputType(InputType.TYPE_CLASS_TEXT);
							password.setTransformationMethod(HideReturnsTransformationMethod
									.getInstance());// show password
						} else {
							show_hide_password.setText(R.string.show_pwd);// change
							// checkbox
							// text

							password.setInputType(InputType.TYPE_CLASS_TEXT
									| InputType.TYPE_TEXT_VARIATION_PASSWORD);
							password.setTransformationMethod(PasswordTransformationMethod
									.getInstance());// hide password

						}

					}
				});
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.loginBtn:
				userlogin();

				break;

			case R.id.forgot_password:

				break;



			case R.id.createAccount:


				Intent intent =new Intent(getApplicationContext(),newsignup.class);
				startActivity(intent);
				break;
		}



	}

	private void userlogin() {



		String email = emailid.getText().toString();

		String pass = password.getText().toString();




		if (email.isEmpty()) {
			emailid.setError("Enter your emailid");
			emailid.requestFocus();
		}

		if (!emailid.getText().toString().matches(emailpattern)) {
			emailid.setError("Enter valid emailId");
			emailid.requestFocus();
		}

		if (pass.isEmpty()) {
			password.setError("Enter your password");
			password.requestFocus();
		}
		if (!password.getText().toString().matches(PASSWORD_PATTERN) && password.length() < 8) {
			password.setError("Enter 8 digit valid password");

		}

		progressbar.setVisibility(View.VISIBLE);

		mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(@NonNull Task<AuthResult> task) {
				progressbar.setVisibility(View.GONE);

				if (task.isSuccessful()){
					finish();
					Intent intent =new Intent(getApplicationContext(),newactivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
				}
				else
				{
					Toast.makeText(getApplicationContext(),"Login unsuccessful",Toast.LENGTH_SHORT).show();
				}
			}
		});


	}


}
