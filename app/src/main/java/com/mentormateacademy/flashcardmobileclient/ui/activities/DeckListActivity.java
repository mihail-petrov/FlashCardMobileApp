package com.mentormateacademy.flashcardmobileclient.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.mentormateacademy.flashcardmobileclient.component.baseComponents.BaseActivity;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.edit.deck.NewDeckFragment;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.lists.DecksListFragment;

public class DeckListActivity extends BaseActivity
        implements
            DecksListFragment.fragmentActionCallback,
            NewDeckFragment.fragmentActionCallback
        {

    //  Activity -- Life Cycle
    // =============================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initFragment();
    }

    //  Activity -- Helper Methods
    // =============================================

    public void initFragment() {
        initDeckListFragment();
    }

    public void initDeckListFragment(){
        setToolbarTitle("My Decks");
        setFragment(DecksListFragment.newInstance(), false);
    }

    public void initAddDeckFragment(){
        setToolbarTitle("New Deck");
        setFragment(NewDeckFragment.newInstance(), false);
    }

    //  Activity -- Action Callbacks
    // =============================================

    @Override
    public void onDeckItemAdded() {
        initAddDeckFragment();
    }

    public void onDeckItemSelected() {

        Intent openCardListActivity = new Intent(this, CardListActivity.class);
        startActivity(openCardListActivity);
    }

    @Override
    public void onSettingsSelected() {
        SharedPreferences settings = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear().commit();

        Intent openActivity = new Intent(this, WelcomeActivity.class);
        startActivity(openActivity);
        finish();
    }

            @Override
    public void addNewDeck() {
        initFragment();
    }
}
