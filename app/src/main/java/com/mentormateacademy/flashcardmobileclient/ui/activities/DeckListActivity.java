package com.mentormateacademy.flashcardmobileclient.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.lists.DecksListFragment;

public class DeckListActivity extends ActionBarActivity {

    private Toolbar topToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_view);

        //
        topToolbar = (Toolbar) findViewById(R.id.topToolbar);
        setSupportActionBar(topToolbar);

        getSupportActionBar().setTitle("My Decks");


        //
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer, new DecksListFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_deck, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.add_new_deck) {
            Intent openNewDeckActivity = new Intent(DeckListActivity.this, NewDeckActivity.class);
            startActivity(openNewDeckActivity);
            return true;
        }

        return false;
    }
}
