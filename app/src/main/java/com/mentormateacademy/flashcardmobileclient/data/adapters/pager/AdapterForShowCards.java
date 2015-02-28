package com.mentormateacademy.flashcardmobileclient.data.adapters.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mentormateacademy.flashcardmobileclient.models.Card;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.single_card_fragment.BackFragment;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.single_card_fragment.ExtraFragment;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.single_card_fragment.FrontFragment;

public class AdapterForShowCards extends FragmentStatePagerAdapter {

    private Card cardElement;


    public AdapterForShowCards(FragmentManager fm, Card cardObject) {
        super(fm);

        this.cardElement = cardObject;
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0) {
            return FrontFragment.newInstance(this.cardElement.getFrontData());
        }

        if(position == 1) {
            return BackFragment.newInstance(this.cardElement.getBackData());
        }

        if(position == 2) {
            return ExtraFragment.newInstance(this.cardElement.getExtraData());
        }

        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {
            return this.cardElement.getFrontTitle();
        }

        if(position == 1) {
            return this.cardElement.getBackTitle();
        }

        if(position == 2) {
            if(this.cardElement.getExtraTitle() == null) {
                return "Extra";
            }
            else {
                return this.cardElement.getExtraTitle();
            }
        }

        return null;
    }

    public void setNewCard(Card element){
        this.cardElement = element;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
