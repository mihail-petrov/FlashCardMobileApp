package com.mentormateacademy.flashcardmobileclient.data.adapters.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mentormateacademy.flashcardmobileclient.ui.fragments.edit.card.NewBackCardFragment;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.edit.card.NewExtraCardFragment;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.edit.card.NewFrontCardFragment;

public class PagerAdapterForCards extends FragmentStatePagerAdapter {

    public PagerAdapterForCards(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0) {
            return NewFrontCardFragment.newInstance(null);
        }

        if(position == 1) {
            return NewBackCardFragment.newInstance(null);
        }

        if(position == 2) {
            return NewExtraCardFragment.newInstance(null);
        }

        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {
            return "Front";
        }

        if(position == 1) {
            return "Back";
        }

        if(position == 2) {
            return "Extra";
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
