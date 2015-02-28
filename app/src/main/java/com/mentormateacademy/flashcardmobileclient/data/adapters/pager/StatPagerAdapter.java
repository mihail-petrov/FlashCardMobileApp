package com.mentormateacademy.flashcardmobileclient.data.adapters.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mentormateacademy.flashcardmobileclient.ui.fragments.stats.StatFragment;


public class StatPagerAdapter extends FragmentStatePagerAdapter {

    private int correct;
    private int wrong;

    public StatPagerAdapter(FragmentManager fm, int correct, int wrong) {
        super(fm);

        this.correct = correct;
        this.wrong   = wrong;
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0) {
            return StatFragment.newInstance(this.correct, this.wrong, "#00ff00");
        }

        if(position == 1) {
            return StatFragment.newInstance(this.wrong, this.correct, "#ff0000");
        }

        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {
            return "Correct Answers";
        }

        if(position == 1) {
            return "Wrong Answers";
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
