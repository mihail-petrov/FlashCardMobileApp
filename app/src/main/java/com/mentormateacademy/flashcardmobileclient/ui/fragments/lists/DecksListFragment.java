package com.mentormateacademy.flashcardmobileclient.ui.fragments.lists;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mentormateacademy.flashcardmobileclient.R;

public class DecksListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_list_deck, container, false);
        ListView deckListView = (ListView) fragmentView.findViewById(R.id.newCardContentEditText);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
