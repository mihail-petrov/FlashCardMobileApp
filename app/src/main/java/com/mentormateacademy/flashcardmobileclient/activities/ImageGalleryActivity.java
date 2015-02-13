package com.mentormateacademy.flashcardmobileclient.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mentormateacademy.flashcardmobileclient.R;

public class ImageGalleryActivity extends ActionBarActivity {

    private ImageView flashCardImageView;
    private Button getImageButton;

    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_galery);

        //
        getImageButton = (Button) findViewById(R.id.getImageButton);

        flashCardImageView = (ImageView) findViewById(R.id.flashCardImageView);


        if (savedInstanceState != null) {
            if (savedInstanceState.getString("IMG_ELEMENT") != null) {

                imagePath = savedInstanceState.getString("IMG_ELEMENT");

                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                flashCardImageView.setImageBitmap(bitmap);

            }
        }

        getImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //
                Intent getImageFromGallery = new Intent();
                getImageFromGallery.setType("image/*");
                getImageFromGallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(getImageFromGallery, "Select Image"), 1);
            }
        });
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("IMG_ELEMENT", imagePath);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {

                Uri selectedImageUri = data.getData();

                //
                imagePath = getPath(selectedImageUri);

                //
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                flashCardImageView.setImageBitmap(bitmap);
            }
        }
    }

    public String getPath(Uri uri) {
        if (uri == null) {
            return null;
        }

        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(columnIndex);
        }

        return uri.getPath();
    }
}
