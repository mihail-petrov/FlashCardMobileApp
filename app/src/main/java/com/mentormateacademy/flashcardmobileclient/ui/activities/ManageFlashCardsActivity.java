package com.mentormateacademy.flashcardmobileclient.ui.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mentormateacademy.flashcardmobileclient.R;

public class ManageFlashCardsActivity extends ActionBarActivity {

    public static final String SELECTED_DECK_NAME = "deckName";

    private Toolbar toolbar;

    private static boolean fragmentAdded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_flash_cards);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(!fragmentAdded) {
            DeckFragment deckFragment = new DeckFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(R.id.container, deckFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();

            fragmentAdded = true;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manage_flash_cards, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == android.R.id.home) {
            Log.d("Up button", "Up button pressed");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            fragmentAdded = false;
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DeckFragment deckFragment = null;
        try{
            deckFragment = (DeckFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        }catch (ClassCastException e){}
        if(deckFragment != null) {
            Log.d("DeckFragment", "onBackPressed() Called");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            fragmentAdded = false;
            finish();
        }else{
            super.onBackPressed();
        }
    }



}
