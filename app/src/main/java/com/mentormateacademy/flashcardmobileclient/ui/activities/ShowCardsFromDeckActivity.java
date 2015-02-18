package com.mentormateacademy.flashcardmobileclient.ui.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.configurations.StrategyConfiguration;
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
    private int cardIndex = 1;
    private long strategyId;

    // is correct
    private boolean isCardCorrect = false;

    // count
    private int cardCount;

    //
    private int correctAnswer = 0;
    private int wrongAnswers  = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_view);

        // Set Toolbar component
        topToolbar = (Toolbar) findViewById(R.id.topToolbar);
        setSupportActionBar(topToolbar);
        getSupportActionBar().setTitle("TODO: Deck Name ");

        // get all deck elements
        long deckId = getIntent().getLongExtra("DECK_ID", 0);
        strategyId = getIntent().getLongExtra("STRATEGY_ID", 0);

        // Init card array list
        Bundle arguments = new Bundle();
        arguments.putString("deck_id", String.valueOf(deckId));
        cards = DatabaseRepository.getRepository(this).getCardRepository().readBy(arguments);
        cardCount = cards.size();
        // initialize first fragment view
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
    public void createCard(int index) {

        createCardFragment(index);

        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.slide_up, R.animator.slide_down)
                .replace(R.id.fragmentContainer, frontFragment).commit();
    }

    public void createCardFragment(int index) {

        boolean result = cards.iterator().hasNext();
        Log.d("ITERATOR_RESULT", "Result --> " + result + " total count " + cardCount);


        if (index < cardCount) {
            // get card object
            Card cardObject = cards.get(index);

            String frontTitle = cardObject.getFrontTitle();
            String frontContent = cardObject.getFrontContent();

            String backTitle = cardObject.getBackTitle();
            String backContent = cardObject.getBackContent();

            // check if this object exists
            String extraTitle = cardObject.getExtraTitle();
            String extraContent = cardObject.getExtraContent();

            //
            frontFragment = FrontFragment.newInstance(frontTitle, frontContent);
            backFragment = BackFragment.newInstance(backTitle, backContent);

            if (extraTitle != null && extraContent != null) {
                extraFragment = ExtraFragment.newInstance(extraTitle, extraContent);
            }

        } else {
            Intent openStatActivity = new Intent(this, StatActivity.class);

            Log.d("BEFORE_SEND", " " + correctAnswer + " " + wrongAnswers + " " + cardCount);

            openStatActivity.putExtra("CORECT", correctAnswer);
            openStatActivity.putExtra("WRONG", wrongAnswers);
            openStatActivity.putExtra("TOTAL", cardCount);
            startActivity(openStatActivity);
        }
    }

    //
    // ===================================================

    public boolean onCreateOptionsMenu(Menu menu) {

        // SPACED REPETITION
        // ===================================================
        if (strategyId == StrategyConfiguration.SPACED_REPETITION) {
            getMenuInflater().inflate(R.menu.menu_strategy_spaced, menu);
        }

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (strategyId == StrategyConfiguration.SPACED_REPETITION) {

            if (item.getItemId() == R.id.correct) {
                this.isCardCorrect = true;
                invalidateOptionsMenu();
                Log.d("MENU_LOG", "CORRECT_IS_CORRECT");

                correctAnswer++;
            }

            if (item.getItemId() == R.id.incorrect) {
                this.isCardCorrect = true;
                invalidateOptionsMenu();
                Log.d("MENU_LOG", "WRONG_IS_WRONG");

                wrongAnswers++;
            }
        }


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
            isCardCorrect = false;
        }

        return false;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (isCardCorrect) {
            menu.clear();
            getMenuInflater().inflate(R.menu.menu_card_show, menu);
            invalidateOptionsMenu();
        } else {
            menu.clear();
            getMenuInflater().inflate(R.menu.menu_strategy_spaced, menu);
            //invalidateOptionsMenu();
        }

        return true;
    }
}
