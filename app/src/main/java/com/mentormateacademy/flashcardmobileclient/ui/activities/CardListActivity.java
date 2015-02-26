package com.mentormateacademy.flashcardmobileclient.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.component.baseComponents.BaseActivity;
import com.mentormateacademy.flashcardmobileclient.data.singletons.DataHolder;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.edit.deck.EditDeckFragment;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.example_nested_fragment.ExperimentalShowCardFragment;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.experimental.AddCardViewFragment;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.lists.CardListFragment;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.stats.ViewPagerFragment;

public class CardListActivity extends BaseActivity
        implements
            CardListFragment.fragmentActionCallback,
            EditDeckFragment.fragmentActionCallback,
            AddCardViewFragment.fragmentActionCallback,
        ExperimentalShowCardFragment.fragmentActionCallback
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //
        initFragment();
    }

    // Activity - Fragment constructors
    // =========================================================

    // * CARD LIST FRAGMENT
    public void initFragment(){
        initCardListFragment();
    }

    public void initCardListFragment() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setToolbarTitle(DataHolder.getData().getDeckTitle());
        setFragment(CardListFragment.newInstance(), false);
    }

    public void initShowCardFragment() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setFragment(ExperimentalShowCardFragment.newInstance(), false);
    }

    public void initStatFragment() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setToolbarTitle("Card Stats");
        setFragment(ViewPagerFragment.newInstance(), false);
    }

    public void initAddCardFragment() {
        // experimental
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setToolbarTitle("Add Card");
        setFragment(AddCardViewFragment.newInstance(), false);

    }

    public void initEditDeckFragment(){

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setToolbarTitle("Edit Deck");
        setFragment(EditDeckFragment.newInstance(), false);
    }

    // Activity - Menu
    // =========================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.deck_menu, menu);

        // check if some collection is already showed
        boolean showCardStat = DataHolder.getData().hasStatistic();
        menu.findItem(R.id.card_stat).setVisible(showCardStat);

        // check if the deck is empty
        boolean showCardPlay = (DataHolder.getData().getCardSize() > 1);
        menu.findItem(R.id.play_card).setVisible(showCardPlay);

        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // play card collection
        if(item.getItemId() == R.id.play_card)
        {
            initShowCardFragment();
            return true;
        }

        // Add card
        if(item.getItemId() == R.id.add_card) {
            initAddCardFragment();
            return true;
        }

        // Card Stat
        if(item.getItemId() == R.id.card_stat) {
            initStatFragment();
            return true;
        }

        // Edit Deck
        if(item.getItemId() == R.id.edit_deck) {
            initEditDeckFragment();
            return true;
        }

        // show list view
        if(item.getItemId() == R.id.list_view) {
            initCardListFragment();
            return true;
        }

        return false;
    }

    // Activity - Action Callbacks
    // ========================================================

    @Override
    public void onCardItemSelected() {
        Intent openActivity = new Intent(this, CardActivity.class);
        startActivity(openActivity);
    }

    @Override
    public void onDeckItemEdit() {
        initFragment();
    }


    @Override
    public void onCardSaved() {
        initFragment();
        invalidateOptionsMenu();
    }

    @Override
    public void onCardSHowFinish() {
        initStatFragment();
    }
}
