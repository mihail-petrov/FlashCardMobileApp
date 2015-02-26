package com.mentormateacademy.flashcardmobileclient.data.adapters.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mentormateacademy.flashcardmobileclient.models.Card;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.edit.card.NewBackCardFragment;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.edit.card.NewExtraCardFragment;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.edit.card.NewFrontCardFragment;

public class PagerAdapterForEditCards extends FragmentStatePagerAdapter {

    private Card cardElement;

    public PagerAdapterForEditCards(FragmentManager fm, Card cardObject) {
        super(fm);

        this.cardElement = cardObject;
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0) {
            return NewFrontCardFragment.newInstance(this.cardElement.getFrontData());
        }

        if(position == 1) {
            return NewBackCardFragment.newInstance(this.cardElement.getBackData());
        }

        if(position == 2) {
            return NewExtraCardFragment.newInstance(this.cardElement.getExtraData());
        }

        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {
            //return "Front";
            return this.cardElement.getFrontTitle();
        }

        if(position == 1) {
            //return "Back";
            return this.cardElement.getBackTitle();
        }

        if(position == 2) {
            //return "Extra";
            if(this.cardElement.getExtraTitle() == null) {
                return "Extra";
            }
            else {
                return this.cardElement.getExtraTitle();
            }
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
