package com.mentormateacademy.flashcardmobileclient.data.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mentormateacademy.flashcardmobileclient.ui.fragments.edit.card.NewBackCardFragment;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.edit.card.NewExtraCardFragment;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.edit.card.NewFrontCardFragment;

public class CardPagerAdapter extends FragmentPagerAdapter {

    public CardPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return NewFrontCardFragment.newInstance();
        }

        if (position == 1) {
            return NewBackCardFragment.newInstance();
        }

        if (position == 2) {
            return NewExtraCardFragment.newInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "FRONT";
        }

        if (position == 1) {
            return "BACK";
        }

        if (position == 2) {
            return "EXTRA";
        }

        return null;
    }
}
