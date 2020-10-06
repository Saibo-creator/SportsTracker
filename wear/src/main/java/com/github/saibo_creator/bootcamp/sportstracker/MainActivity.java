package com.github.saibo_creator.bootcamp.sportstracker;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

public class MainActivity extends WearableActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView test = findViewById(R.id.test);
        test.setText("Hello Round World!");


        // Enables Always-on
        setAmbientEnabled();
    }
}
