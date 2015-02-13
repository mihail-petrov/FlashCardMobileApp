package com.mentormateacademy.flashcardmobileclient.helpers;

import android.content.Context;
import android.util.DisplayMetrics;

public class MetricHelper {

    private static MetricHelper instance = null;

    private int widthPixel;
    private int heightPixel;

    private float widthInInches;
    private float heightInInches;
    private double diagonalInInches;

    private Context context;

    private MetricHelper(Context context) {
        this.context = context;
        getScreenSize();
    }

    public static MetricHelper getScreenMetrics(Context context) {
        if (instance == null) {
            instance = new MetricHelper(context);
        }
        return instance;
    }

    public void getScreenSize() {

        DisplayMetrics screenMetrics = this.context.getResources().getDisplayMetrics();

        //
        this.widthPixel = screenMetrics.widthPixels;
        this.heightPixel = screenMetrics.heightPixels;

        //
        float widthDpi = screenMetrics.xdpi;
        float heightDpi = screenMetrics.ydpi;

        this.widthInInches = (this.widthPixel / widthDpi);
        this.heightInInches = (this.heightPixel / heightDpi);

        this.diagonalInInches = Math.sqrt((this.widthInInches * this.widthInInches) +
                (this.heightInInches * this.heightInInches));
    }


    public int getScreenWidthInPixel() {
        return this.widthPixel;
    }

    public int getScreenHeightInPixel() {
        return this.heightPixel;
    }

    public float getWidthInInches() {
        return this.widthInInches;
    }

    public float getHeightInInches() {
        return this.heightInInches;
    }

    public double getDiagonalInInches() {
        return this.diagonalInInches;
    }

    public int getScreenOrientation() {
        return this.context.getResources().getConfiguration().orientation;
    }
}
