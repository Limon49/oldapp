package com.login_signup_screendesign_demo;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class newsignup extends AppCompatActivity implements View.OnClickListener{
    private static Button owner;
    private static Button renter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsignup);


        initViews();
        setListeners();

        return ;
    }



    private void initViews()
    {
        owner =(Button)findViewById(R.id.owner);
        renter =(Button)findViewById(R.id.rentor);
    }


    private void setListeners()
    {
        owner.setOnClickListener((View.OnClickListener) this);
        renter.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.owner:
                Intent intent =new Intent(getApplicationContext(),signupforowner.class);
                startActivity(intent);
                break;
            case R.id.rentor:

                Intent intent1 =new Intent(getApplicationContext(),signupforrenter.class);
                startActivity(intent1);
                break;
        }
    }
}
