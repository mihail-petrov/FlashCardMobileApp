package com.mentormateacademy.flashcardmobileclient.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.edit.deck.NewDeckFragment;

public class NewDeckActivity extends ActionBarActivity
        implements NewDeckFragment.onAddNewDeckCallback {

    private Toolbar topToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_view);

        //
        topToolbar = (Toolbar) findViewById(R.id.topToolbar);
        setSupportActionBar(topToolbar);

        getSupportActionBar().setTitle("New Deck");

        // show fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer, new NewDeckFragment())
                .commit();
    }


    @Override
    public void addNewDeck() {
        Intent startActivityBack = new Intent(NewDeckActivity.this, DeckListActivity.class);
        startActivity(startActivityBack);
    }
}
