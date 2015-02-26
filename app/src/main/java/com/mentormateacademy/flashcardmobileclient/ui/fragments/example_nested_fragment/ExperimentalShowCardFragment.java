package com.mentormateacademy.flashcardmobileclient.ui.fragments.example_nested_fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.component.androidComponents.SlidingTabLayout;
import com.mentormateacademy.flashcardmobileclient.component.baseComponents.ContainerFragment;
import com.mentormateacademy.flashcardmobileclient.component.customComponents.viewPager.CustomViewPager;
import com.mentormateacademy.flashcardmobileclient.data.adapters.pager.AdapterForShowCards;
import com.mentormateacademy.flashcardmobileclient.data.singletons.DataHolder;
import com.mentormateacademy.flashcardmobileclient.database.helper.DatabaseRepository;
import com.mentormateacademy.flashcardmobileclient.database.repositories.CardRepository;
import com.mentormateacademy.flashcardmobileclient.database.repositories.DeckRepository;
import com.mentormateacademy.flashcardmobileclient.models.Card;
import com.mentormateacademy.flashcardmobileclient.models.Deck;

import java.util.ArrayList;

public class ExperimentalShowCardFragment extends ContainerFragment implements View.OnClickListener {

    private CustomViewPager viewPager;

    private AdapterForShowCards cardAdapter;


    // Fragment - Properties
    // ====================================

    // UI control variables
    private boolean isFront = true;
    private int cardIndex   = 1;

    // count
    private int cardCount;

    //
    private int correctAnswer = 0;
    private int wrongAnswers  = 0;

    //
    private LinearLayout gradeGroup;

    private ImageView nextScreen;

    //
    private Bundle deckFragmentArguments;
    private Bundle cardFragmentArguments;

    //
    private DeckRepository deckRepository;
    private CardRepository cardRepository;

    //
    private Deck deckElement;
    private ArrayList<Card> cardElements;

    private SlidingTabLayout tabs;

    public ExperimentalShowCardFragment(){
    }

    public static ExperimentalShowCardFragment newInstance(){
        ExperimentalShowCardFragment fragment = new ExperimentalShowCardFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mActivity = (fragmentActionCallback) activity;

        // set primitive values
        long deckId      = DataHolder.getData().getDeckId();

        // create deck fragment arguments bundle
        deckFragmentArguments = new Bundle();
        deckFragmentArguments.putString("_id", String.valueOf(deckId));

        // create card fragment argument Bundle
        cardFragmentArguments = new Bundle();
        cardFragmentArguments.putString("deck_id", String.valueOf(deckId));

        // get database items
        deckRepository = DatabaseRepository.getRepository(getActivity()).getDeckRepository();
        cardRepository = DatabaseRepository.getRepository(getActivity()).getCardRepository();

        // det database objects
        deckElement  = deckRepository.readBy(deckFragmentArguments).get(0);
        cardElements = cardRepository.readBy(cardFragmentArguments);

        // set object properties
        //cardCount = cardElements.size();
        cardCount = DataHolder.getData().getCardSize();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View fragmentView = getFragmentView();

        // ViewPager Adapter
        cardAdapter = new AdapterForShowCards(getActivity().getSupportFragmentManager(), null);

        //
        viewPager = (CustomViewPager) fragmentView.findViewById(R.id.viewPager);

        initView(0);


        //viewPager.setAdapter(cardAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPagingEnabled(false);

        tabs = (SlidingTabLayout) fragmentView.findViewById(R.id.viewPagerTabs);
        tabs.setDistributeEvenly(true);
        tabs.setViewPager(viewPager);
        tabs.setVisibility(View.GONE);

        //
        inflateBottomToolbar(R.layout.toolbar_show_card);

        // GRADE GROUP
        gradeGroup = (LinearLayout) fragmentView.findViewById(R.id.grade_group);

        // FRONT SCREEN
        ImageView frontScreen = (ImageView) fragmentView.findViewById(R.id.front_screen);
        frontScreen.setOnClickListener(this);

        // BACK SCREEN
        ImageView backScreen = (ImageView) fragmentView.findViewById(R.id.back_screen);
        backScreen.setOnClickListener(this);

        // next element
        nextScreen = (ImageView) fragmentView.findViewById(R.id.next_screen);
        nextScreen.setOnClickListener(this);

        return fragmentView;
    }


    public void initView(int index) {

        if(cardIndex < cardCount ) {
            Card cardObject = cardElements.get(index);
            cardAdapter.setNewCard(cardObject);
            viewPager.setAdapter(cardAdapter);
        }
        else {

            // Save Correct - Wrong answers
            deckElement.setCorrectAnswers(correctAnswer);
            deckElement.setWrongAnswers(wrongAnswers);
            deckRepository.update(deckElement, deckFragmentArguments);

            DataHolder.getData().setCorrect(correctAnswer);
            DataHolder.getData().setWrong(wrongAnswers);

            mActivity.onCardSHowFinish();
        }
    }

    public void moveToNextFragment(int index){
        initView(index);
    }


    // Activity - Event Handler
    // ===================================================

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.front_screen) {
            correctAnswer++;

            //
            nextScreen.setVisibility(View.VISIBLE);
            gradeGroup.setVisibility(View.GONE);
            tabs.setVisibility(View.VISIBLE);

            //
            viewPager.setPagingEnabled(true);
        }

        if(v.getId() == R.id.back_screen) {
            wrongAnswers++;

            nextScreen.setVisibility(View.VISIBLE);
            gradeGroup.setVisibility(View.GONE);
            tabs.setVisibility(View.VISIBLE);

            //
            viewPager.setPagingEnabled(true);
        }

        //
        if(v.getId() == R.id.next_screen) {

            moveToNextFragment(cardIndex);
            cardIndex++;

                nextScreen.setVisibility(View.GONE);
                gradeGroup.setVisibility(View.VISIBLE);
                tabs.setVisibility(View.GONE);

            //
            viewPager.setPagingEnabled(false);
        }
    }

    //
    // =========================================================
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.card_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.next_element) {
            moveToNextFragment(cardIndex);
            cardIndex++;
        }

        return super.onOptionsItemSelected(item);
    }

    // Activity - Event Handler
    // ===================================================

    private fragmentActionCallback mActivity;

    public interface fragmentActionCallback {
        public void onCardSHowFinish();
    }

}
