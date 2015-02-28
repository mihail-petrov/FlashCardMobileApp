package com.mentormateacademy.flashcardmobileclient.ui.fragments.deck_card_fragment;

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
import com.mentormateacademy.flashcardmobileclient.models.Card;

public class SingleShowCardFragment extends ContainerFragment{

    // Fragment - Properties
    // ====================================
    private Card singleCardElement;

    // Fragment - Constructor
    // ====================================
    public SingleShowCardFragment(){
    }

    public static SingleShowCardFragment newInstance(){
        return new SingleShowCardFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // get card repository
        CardRepository cardRepository = DatabaseRepository.getRepository(getActivity()).getCardRepository();

        // get card element
        Bundle cardArguments = new Bundle();
        cardArguments.putString("_id", String.valueOf(DataHolder.getData().getCardId()));

        singleCardElement = cardRepository.readBy(cardArguments).get(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View fragmentView = getFragmentView();


        // ViewPager Adapter
        AdapterForShowCards cardAdapter = new AdapterForShowCards(getActivity().getSupportFragmentManager(), singleCardElement);
        CustomViewPager viewPager = (CustomViewPager) fragmentView.findViewById(R.id.viewPager);
        cardAdapter.setNewCard(singleCardElement);
        viewPager.setAdapter(cardAdapter);

        viewPager.setOffscreenPageLimit(3);
        viewPager.setPagingEnabled(false);

        SlidingTabLayout tabs = (SlidingTabLayout) fragmentView.findViewById(R.id.viewPagerTabs);
        tabs.setDistributeEvenly(true);
        tabs.setViewPager(viewPager);

        return fragmentView;
    }
}
