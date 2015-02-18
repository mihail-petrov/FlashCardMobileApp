package com.mentormateacademy.flashcardmobileclient.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.mentormateacademy.flashcardmobileclient.component.customComponent.canvasView.CanvasView;

/**
 * Created by p_m_t_000 on 2/18/2015.
 */
public class StatActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.sample_stat_activity_view);

//        Intent intent = getIntent();
//
//        int correctA = intent.getIntExtra("CORECT", 0);
//
//        TextView correct = (TextView) findViewById(R.id.correct);
//        correct.setText("CORRECT :" + correctA);
//
//        int wrongA = intent.getIntExtra("WRONG", 0);
//
//        TextView wrong= (TextView) findViewById(R.id.wrong);
//        wrong.setText("WRONG :" + wrongA);
//
//        int totalC = intent.getIntExtra("TOTAL", 0);
//
//        TextView tottal = (TextView) findViewById(R.id.total);
//        tottal.setText("TOTAL :" + totalC);


        CanvasView view = new CanvasView(this);
        setContentView(view);

    }
}
