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


public class MyProfileFragment extends Fragment {



    public Profile userProfile;
    public View fragmentView;

    public MyProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_my_profile,
                container, false);
        Intent intent = getActivity().getIntent();
        userProfile = (Profile) intent.getSerializableExtra("userProfile");
        setUserImageAndWelcomeMessage();
        sendProfileToWatch();

        return fragmentView;
    }

    private void sendProfileToWatch() {
        Intent intentWear = new Intent(getActivity(),WearService.class);
        intentWear.setAction(WearService.ACTION_SEND.PROFILE_SEND.name());
        intentWear.putExtra(WearService.PROFILE,userProfile);
        getActivity().startService(intentWear);
    }

    private void setUserImageAndWelcomeMessage() {
        String imageFilePath=userProfile.photoPath;
        File imageFile = new File(imageFilePath);

        final InputStream imageStream;
        try {
            imageStream =  getActivity().getContentResolver()
                    .openInputStream(Uri.fromFile(imageFile));
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            ImageView imageView =  fragmentView.findViewById(R.id.userImage);
            imageView.setImageBitmap(selectedImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public interface OnFragmentInteractionListener {
    }
}