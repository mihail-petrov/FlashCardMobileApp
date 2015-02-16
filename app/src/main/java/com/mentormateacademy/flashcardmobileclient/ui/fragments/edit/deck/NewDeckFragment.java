package com.mentormateacademy.flashcardmobileclient.ui.fragments.edit.deck;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mentormateacademy.flashcardmobileclient.R;

public class NewDeckFragment extends Fragment {

    public NewDeckFragment() {

    }

    public static NewDeckFragment newInstance() {

        NewDeckFragment fragmentComponent = new NewDeckFragment();
        return fragmentComponent;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_new_deck, container, false);

        // Init view component
        EditText deckTitleEditText = (EditText) fragmentView.findViewById(R.id.deckTitleEditText);
        Button addNewDeckButton = (Button) fragmentView.findViewById(R.id.addNewDeckButton);

        return fragmentView;
    }

    // CALLBACK INTERFACE
    public interface onAddNewDeckCallback {
        public void addNewDeck();
    }
}
