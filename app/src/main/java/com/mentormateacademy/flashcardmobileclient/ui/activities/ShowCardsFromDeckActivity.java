package com.mentormateacademy.flashcardmobileclient.ui.activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.database.helper.DatabaseRepository;
import com.mentormateacademy.flashcardmobileclient.models.Card;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.card_animation_fragment.BackFragment;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.card_animation_fragment.ExtraFragment;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.card_animation_fragment.FrontFragment;

import java.util.ArrayList;


public class ShowCardsFromDeckActivity extends ActionBarActivity {

    // fragments
    private FrontFragment frontFragment;
    private BackFragment backFragment;
    private ExtraFragment extraFragment;

    // Action components
    private Toolbar topToolbar;
    private Menu myMenu;

    // card object container
    private ArrayList<Card> cards;

    // UI control variables
    private boolean isFront = true;
    private int cardIndex   = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_view);

        // Set Toolbar component
        topToolbar = (Toolbar) findViewById(R.id.topToolbar);
        setSupportActionBar(topToolbar);
        getSupportActionBar().setTitle("TODO: Deck Name ");

        // get all deck elements
        long id = getIntent().getLongExtra("DECK_ID", 0);
        cards = DatabaseRepository.getRepository(this).getCardRepository().readAllBaseOnDeckIdObject(id);

        initView();
    }



    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void initView() {

        // first card element
        // TODO : base on strategy have t get this from the database
        createCard(0);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void moveToNextCard(int index) {
        createCard(index);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void flipCard() {

        if (!isFront) {
            getFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                            R.animator.card_flip_left_in, R.animator.card_flip_left_out)
                    .replace(R.id.fragmentContainer, frontFragment).commit();

            topToolbar.setTitle("Sample title");

        } else {

            getFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                            R.animator.card_flip_left_in, R.animator.card_flip_left_out)
                    .replace(R.id.fragmentContainer, backFragment).commit();

            topToolbar.setTitle("Mample title");
        }
    }



    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void createCard(int index){

        createCardFragment(index);

        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.slide_up, R.animator.slide_down)
                .replace(R.id.fragmentContainer, frontFragment).commit();
    }

    public void createCardFragment(int index){

        // get card object
        Card cardObject = cards.get(index);

        String frontTitle   = cardObject.getFrontTitle();
        String frontContent = cardObject.getFrontContent();

        String backTitle   = cardObject.getBackTitle();
        String backContent = cardObject.getBackContent();

        // check if this object exists
        String extraTitle   = cardObject.getExtraTitle();
        String extraContent = cardObject.getExtraContent();

        //
        frontFragment = FrontFragment.newInstance(frontTitle, frontContent);
        backFragment  = BackFragment.newInstance(backTitle, backContent);

        if(extraTitle != null && extraContent != null) {
            extraFragment = ExtraFragment.newInstance(extraTitle, extraContent);
        }
    }

    //
    // ===================================================

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_card_show, menu);
        myMenu = menu;

        if (isFront) {
            myMenu.findItem(R.id.flip).setTitle("Back");
        } else {
            myMenu.findItem(R.id.flip).setTitle("Front");
        }

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.flip) {

            flipCard();
            invalidateOptionsMenu();
            isFront = (isFront) ? false : true;

            return true;
        }

        if (item.getItemId() == R.id.next) {

            moveToNextCard(cardIndex);
            cardIndex++;
            isFront = true;
        }

        return false;
    }


}
