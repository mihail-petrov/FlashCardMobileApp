package com.mentormateacademy.flashcardmobileclient.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.mentormateacademy.flashcardmobileclient.component.customComponent.canvasView.CanvasView;

public class StatActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent   = getIntent();
        int correctA    = intent.getIntExtra("CORECT", 0);
        int wrongA      = intent.getIntExtra("WRONG", 0);
        int totalC      = intent.getIntExtra("TOTAL", 0);

        CanvasView view = new CanvasView(this, correctA, totalC, true);
        setContentView(view);
    }
}
