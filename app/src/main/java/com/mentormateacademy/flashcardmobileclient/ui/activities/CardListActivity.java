package com.mentormateacademy.flashcardmobileclient.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.lists.CardListFragment;

public class CardListActivity extends ActionBarActivity {

    private Toolbar topToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_view);

        //
        topToolbar = (Toolbar) findViewById(R.id.topToolbar);
        setSupportActionBar(topToolbar);
        getSupportActionBar().setTitle("TODO: Deck Name ");

        //
        long deckId = getIntent().getLongExtra("DECK_ID", 0);
        long strategyId = getIntent().getLongExtra("STRATEGY_ID", 0);

        // create listFragment
        CardListFragment cardListFragment = CardListFragment.newInstance(deckId, strategyId);

        //
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer, cardListFragment)
                .commit();
    }
}
