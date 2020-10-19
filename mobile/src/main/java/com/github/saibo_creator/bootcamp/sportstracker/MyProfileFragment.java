package com.github.saibo_creator.bootcamp.sportstracker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile userProfile;
    public View fragmentView;

    public MyProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyProfileFragment newInstance(String param1, String param2) {
        MyProfileFragment fragment = new MyProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_my_profile,
                container, false);
        Intent intent = getActivity().getIntent();
        Profile userProfile = (Profile) intent.getSerializableExtra("userProfileWelcome");
        setUserImageAndWelcomeMessage();
        sendProfileToWatch();

        return fragmentView;
    }

    private void sendProfileToWatch() {
    }

    private void setUserImageAndWelcomeMessage() {
        String imageFilePath=userProfile.photoPath;
        File imageFile = new File(imageFilePath);

        final InputStream imageStream;
        try {
            imageStream =  getActivity().getContentResolver()
                    .openInputStream(Uri.fromFile(imageFile));
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            ImageView imageView =  getActivity().findViewById(R.id.userImage);
            imageView.setImageBitmap(selectedImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }




}