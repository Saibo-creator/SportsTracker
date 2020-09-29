package com.github.saibo_creator.bootcamp.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button = findViewById(R.id.RegisterButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = findViewById(R.id.LoginMessage);
                textView.setText("We used the Java callback!");
            }
        });

    }



    public void clickedLoginButtonXmlCallback(View view) {
        TextView textView = findViewById(R.id.LoginMessage);
        textView.setText("We used the XML callback!");
    }
}