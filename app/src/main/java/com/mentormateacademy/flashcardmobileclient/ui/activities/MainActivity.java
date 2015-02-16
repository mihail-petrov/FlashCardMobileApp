package com.mentormateacademy.flashcardmobileclient.ui.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.component.SlidingTabLayout;
import com.mentormateacademy.flashcardmobileclient.data.adapters.CardPagerAdapter;

public class MainActivity extends ActionBarActivity {

    private Toolbar topToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        topToolbar = (Toolbar) findViewById(R.id.topToolbar);
        setSupportActionBar(topToolbar);


        //
        CardPagerAdapter cardPagerAdapter = new CardPagerAdapter(getSupportFragmentManager());

        //
        ViewPager viewPager = (ViewPager) findViewById(R.id.cardViewPager);
        viewPager.setAdapter(cardPagerAdapter);

        //
        SlidingTabLayout tabLayout = (SlidingTabLayout) findViewById(R.id.tabLayout);
        tabLayout.setDistributeEvenly(true);
        tabLayout.setViewPager(viewPager);
    }
}
