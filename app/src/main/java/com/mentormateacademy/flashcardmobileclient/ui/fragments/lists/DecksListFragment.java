package com.mentormateacademy.flashcardmobileclient.ui.fragments.lists;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.data.adapters.DeckCursorAdapter;
import com.mentormateacademy.flashcardmobileclient.data.loaders.BaseCursorLoader;
import com.mentormateacademy.flashcardmobileclient.data.loaders.cursorProviders.DeckCursorProvider;
import com.mentormateacademy.flashcardmobileclient.ui.activities.CardListActivity;

public class DecksListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_list_deck, container, false);

        // cursor adapter
        DeckCursorAdapter adapter = new DeckCursorAdapter(getActivity(), null);

        // cursor provider
        DeckCursorProvider cursorProvider = new DeckCursorProvider(getActivity());

        // get cursor loader
        BaseCursorLoader cursorLoader = new BaseCursorLoader(adapter, cursorProvider);

        // get list view
        ListView deckListView = (ListView) fragmentView.findViewById(R.id.deckListView);
        deckListView.setAdapter(cursorLoader.getCursorAdapter());

        // Event Listener
        deckListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // list card view
                Intent openCardListActivity = new Intent(getActivity(), CardListActivity.class);
                openCardListActivity.putExtra("DECK_ID", id);
                openCardListActivity.putExtra("STRATEGY_ID", id);
                startActivity(openCardListActivity);


                // start crd slide show
//                Intent openCardListActivity = new Intent(getActivity(), ShowCardsFromDeckActivity.class);
//                openCardListActivity.putExtra("DECK_ID", id);
//                openCardListActivity.putExtra("STRATEGY_ID", id);
//                startActivity(openCardListActivity);
            }
        });

        getLoaderManager().initLoader(0, null, cursorLoader);

        return fragmentView;
    }
}
