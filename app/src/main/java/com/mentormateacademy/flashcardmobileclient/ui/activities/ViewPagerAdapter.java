package com.mentormateacademy.flashcardmobileclient.ui.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public static final int PAGE_COUNT = 3;
    public static final String INDEX_KEY = "index";

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        NewCardFragment newCardFragment = new NewCardFragment();
        Bundle args = new Bundle();
        args.putInt(INDEX_KEY, i+1);
        newCardFragment.setArguments(args);
        return newCardFragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
