package com.mentormateacademy.flashcardmobileclient.helpers;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraHelper {
    public static File fileObject = null;
    public static String filePath = null;

    public Intent takeAPicture(String filePrefix, String fileExtension, String directoryName) {

        // prepare internal Intent
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // get external storage
        String externalStorageState = Environment.getExternalStorageState();
        boolean isAvailable = Environment.MEDIA_MOUNTED.equals(externalStorageState);

        // prepare a file for storing an image directory
        File imageDirectory = null;

        // check if everything is file
        if (isAvailable) {
            imageDirectory = createFileDirectory(directoryName);
        }

        // get image file name
        String fileName = createFileName(filePrefix, fileExtension);

        // get result image file and its path
        File resultImage = new File(imageDirectory, fileName);

        // save output FIle object
        fileObject = resultImage;
        filePath = resultImage.getAbsolutePath();

        // get file object Uri as an extra parameter
        Uri imageUri = Uri.fromFile(resultImage);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

        return takePictureIntent;
    }

    private String createFileName(String filePrefix, String fileExtension) {
        // create file name
        String timeFormat = "yyyyMMdd_Hmmss";
        String timeStamp = new SimpleDateFormat(timeFormat).format(new Date());

        // set photo name and extension
        String fileName = (filePrefix + timeStamp + "." + fileExtension);

        return fileName;
    }

    private File createFileDirectory(String directoryName) {

        String parentDirectory = "/DCIM/";

        String imageDirectoryName = directoryName;
        String imageDirectoryPath = Environment.getExternalStorageDirectory().toString() + parentDirectory + imageDirectoryName;

        File imageDirectory = new File(imageDirectoryPath);
        if (!imageDirectory.exists()) {
            imageDirectory.mkdirs();
        }

        return imageDirectory;
    }
}
