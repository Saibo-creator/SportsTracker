package com.github.saibo_creator.bootcamp.sportstracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private Button regButton;
    private Button logButton;
    private Profile userProfile = null;
    private static final int REGISTER_PROFILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        regButton= findViewById(R.id.RegisterButton);
        logButton=findViewById(R.id.LoginButton);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEditProfile = new Intent(LoginActivity.this,EditProfileActivity.class);
                startActivityForResult(intentEditProfile, REGISTER_PROFILE);
            }
        });

//        logButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentEnterMainAct = new Intent(LoginActivity.this, MainActivity.class);
//                intentEnterMainAct.putExtra("userProfile", userProfile);
//            }
//        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REGISTER_PROFILE && resultCode == RESULT_OK) {
            assert data != null;
            userProfile = (Profile) data.getSerializableExtra("userProfile");
            if (userProfile != null) {
                TextView username = findViewById(R.id.editUsername);
                username.setText(userProfile.username);
                TextView password = findViewById(R.id.editPassword);
                password.setText(userProfile.password);
            }
            //After getting useProfile,we can
            //now update the OnClickListener of logButton
            logButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentMainAct = new Intent(LoginActivity.this,MainActivity.class);
                    intentMainAct.putExtra("userProfile", userProfile);
                    startActivity(intentMainAct);
                }
            });
        }
    }

    public void clickedLoginButtonXmlCallback(View view) {
        if(userProfile != null) {
            // TODO: start the MainActivity once created (in little time :) )
        } else {
            TextView textView = findViewById(R.id.LoginMessage);
            textView.setText("You are not registered yet!");
            textView.setTextColor(Color.RED);
        }
    }
}