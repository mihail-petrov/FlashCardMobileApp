package com.mentormateacademy.flashcardmobileclient.ui.fragments.lists;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.data.loaders.cursorLoaders.DeckCursorLoader;

public class DecksListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_list_deck, container, false);

        // get cursor loader
        DeckCursorLoader cursorLoader = new DeckCursorLoader(getActivity());

        // get list view component
        ListView deckListView = (ListView) fragmentView.findViewById(R.id.deckListView);
        deckListView.setAdapter(cursorLoader.getCursorAdapter());

        //
        getLoaderManager().initLoader(0, null, cursorLoader);

        return fragmentView;
    }
}
