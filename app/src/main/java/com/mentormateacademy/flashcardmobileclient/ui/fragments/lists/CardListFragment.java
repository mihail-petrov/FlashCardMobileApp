package com.mentormateacademy.flashcardmobileclient.ui.fragments.lists;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.data.adapters.CardCursorAdapter;
import com.mentormateacademy.flashcardmobileclient.data.loaders.BaseCursorLoader;
import com.mentormateacademy.flashcardmobileclient.data.loaders.cursorProviders.CardCursorProvider;

public class CardListFragment extends Fragment {

    public CardListFragment() {

    }

    public static CardListFragment newInstance(long deckId, long strategyId) {

        Bundle arguments = new Bundle();
        arguments.putLong("DECK_ID", deckId);
        arguments.putLong("STRATEGY_ID", strategyId);

        CardListFragment cardListFragment = new CardListFragment();
        cardListFragment.setArguments(arguments);

        return cardListFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_list_cards, container, false);

        long id = getArguments().getLong("DECK_ID");

        // cursor adapter
        CardCursorAdapter cursorAdapter = new CardCursorAdapter(getActivity(), null);

        // cursor provider
        CardCursorProvider cursorProvider = new CardCursorProvider(getActivity(), id);

        // cursor loader
        BaseCursorLoader cursorLoader = new BaseCursorLoader(cursorAdapter, cursorProvider);

        // get list component
        ListView cardListView = (ListView) fragmentView.findViewById(R.id.cardsListView);
        cardListView.setAdapter(cursorLoader.getCursorAdapter());

        //
        getLoaderManager().initLoader(0, null, cursorLoader);

        return fragmentView;
    }
}
