package com.github.saibo_creator.bootcamp.sportstracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class EditProfileActivity extends AppCompatActivity {
    // Usually at the top of the class
    private static final int PICK_IMAGE = 1;
    private File imageFile;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }

    // Anywhere with the other methods of the class
    public void chooseImage(View view) {//是一个callback,在XML中chooseImageBtn定义的

        Intent intent = new Intent();
        intent.setType("image/*");//TODO
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }


    public void editUser(View view){//是一个callback,在XML中editUserBtn定义的

        TextView username = findViewById(R.id.editUsername);
        TextView password = findViewById(R.id.editPassword);
        TextView height = findViewById(R.id.editHeight);
        TextView weight = findViewById(R.id.editWeight);

        Profile userProfile = new Profile(username.getText().toString(),
                password.getText().toString());

        if(height.getText().toString().trim().equalsIgnoreCase("")){
            height.setError("Enter Height");
        }else if(weight.getText().toString().trim().equalsIgnoreCase("")){
            weight.setError("Enter Weight");
        }else {

            try {

                userProfile.height_cm = Integer.valueOf(height.getText().toString());
            } catch (NumberFormatException e) {
                userProfile.height_cm = 0;

            }
            try {
                userProfile.weight_kg = Float.valueOf(weight.getText().toString());
            } catch (NumberFormatException e) {
                userProfile.weight_kg = 0;
            }
            if (imageFile == null) {
                userProfile.photoPath = "";
            } else {
                userProfile.photoPath = imageFile.getPath();
            }

            Intent intent = new Intent(EditProfileActivity.this, LoginActivity.class);
            intent.putExtra("userProfile", userProfile);
            setResult(AppCompatActivity.RESULT_OK, intent);//因为这里我们不光要返回一个result code，还有数据
            //所以需要一个intent来承载data
            finish();


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            imageFile = new File(getExternalFilesDir(null), "profileImage");
            try {
                copyImage(imageUri, imageFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            final InputStream imageStream;
            try {
                imageStream = getContentResolver()
                        .openInputStream(Uri.fromFile(imageFile));
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                ImageView imageView = findViewById(R.id.userImage);
                imageView.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void copyImage(Uri uriInput, File fileOutput) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = getContentResolver().openInputStream(uriInput);
            out = new FileOutputStream(fileOutput);
            // Transfer bytes from in to out
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.close();
        }
    }


}