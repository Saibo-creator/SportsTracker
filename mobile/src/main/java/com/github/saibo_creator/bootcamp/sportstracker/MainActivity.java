package com.github.saibo_creator.bootcamp.sportstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity
        implements  NewRecordingFragment.OnFragmentInteractionListener,
        MyProfileFragment.OnFragmentInteractionListener,
        MyHistoryFragment.OnFragmentInteractionListener {

    Profile userProfile;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent it = getIntent();
        userProfile = (Profile) it.getSerializableExtra("userProfile");
        setUserImageAndWelcomeMessage();
        sendProfileToWatch();
    }

    private void setUserImageAndWelcomeMessage() {
        final InputStream imageStream;
        try {
            String imageFilePath=userProfile.photoPath;
            File imageFile = new File(imageFilePath);

            imageStream = getContentResolver()
                    .openInputStream(Uri.fromFile(imageFile));
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            imageView = findViewById(R.id.userImage);
            imageView.setImageBitmap(selectedImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        TextView welcomeTextView = findViewById(R.id.welcomeText);
        welcomeTextView.setText("Welcome " + userProfile.username + "!");
    }



    private void sendProfileToWatch() {
        Intent intentWear = new Intent(MainActivity.this,WearService.class);
        intentWear.setAction(WearService.ACTION_SEND.PROFILE_SEND.name());
        intentWear.putExtra(WearService.PROFILE,userProfile);
        startService(intentWear);
    }
    }
}
