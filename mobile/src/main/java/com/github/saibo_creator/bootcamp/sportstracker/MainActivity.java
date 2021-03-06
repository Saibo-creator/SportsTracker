package com.github.saibo_creator.bootcamp.sportstracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

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

//    Profile userProfile;
//    private ImageView imageView;
    private SectionStatePagerAdapter mSectionStatePagerAdapter;
    private MyProfileFragment myProfileFragment;
    private NewRecordingFragment newRecFragment;
    private MyHistoryFragment myHistoryFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionStatePagerAdapter = new SectionStatePagerAdapter(getSupportFragmentManager());
        myProfileFragment = new MyProfileFragment();
        newRecFragment = new NewRecordingFragment();
        myHistoryFragment = new MyHistoryFragment();
        ViewPager mViewPager = findViewById(R.id.mainViewPager);
        setUpViewPager(mViewPager);
        // Set NewRecordingFragment as default tab once started the activity
        mViewPager.setCurrentItem(mSectionStatePagerAdapter
                .getPositionByTitle("New Recording"));

    }


    private void setUpViewPager(ViewPager mViewPager) {
        mSectionStatePagerAdapter.addFragment(newRecFragment, "New Recording");
        mSectionStatePagerAdapter.addFragment(myProfileFragment, "My Profile");
        mSectionStatePagerAdapter.addFragment(myHistoryFragment,"My History");
        mViewPager.setAdapter(mSectionStatePagerAdapter);
    }



}
