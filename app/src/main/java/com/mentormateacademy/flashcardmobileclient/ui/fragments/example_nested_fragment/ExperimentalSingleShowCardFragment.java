package com.mentormateacademy.flashcardmobileclient.ui.fragments.example_nested_fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class ExperimentalSingleShowCardFragment extends ContainerFragment{

    private CustomViewPager viewPager;

    private AdapterForShowCards cardAdapter;


    // Fragment - Properties
    // ====================================

    // UI control variables
    private int cardIndex   = 1;

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

    private Card singleCardElement;

    public ExperimentalSingleShowCardFragment(){
    }

    public static ExperimentalSingleShowCardFragment newInstance(){
        ExperimentalSingleShowCardFragment fragment = new ExperimentalSingleShowCardFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

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

        // get card element
        Bundle cardArguments = new Bundle();
        cardArguments.putString("_id", String.valueOf(DataHolder.getData().getCardId()));

        singleCardElement = cardRepository.readBy(cardArguments).get(0);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View fragmentView = getFragmentView();

        // ViewPager Adapter
        cardAdapter = new AdapterForShowCards(getActivity().getSupportFragmentManager(), singleCardElement);
        viewPager   = (CustomViewPager) fragmentView.findViewById(R.id.viewPager);
        cardAdapter.setNewCard(singleCardElement);
        viewPager.setAdapter(cardAdapter);

        //viewPager.setAdapter(cardAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPagingEnabled(false);

        tabs = (SlidingTabLayout) fragmentView.findViewById(R.id.viewPagerTabs);
        tabs.setDistributeEvenly(true);
        tabs.setViewPager(viewPager);

        return fragmentView;
    }
}
