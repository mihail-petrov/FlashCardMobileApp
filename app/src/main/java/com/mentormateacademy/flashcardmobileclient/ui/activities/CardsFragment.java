package com.mentormateacademy.flashcardmobileclient.ui.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.mentormateacademy.flashcardmobileclient.R;

public class CardsFragment extends Fragment{

    private ListView listViewCards;
    private Button buttonStartStudy;
    private String deckName;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        deckName = this.getArguments().getString(ManageFlashCardsActivity.SELECTED_DECK_NAME);
        return inflater.inflate(R.layout.fragment_cards, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializeListView();
        initializeButton();
        getActivity().setTitle(deckName);
    }

    private void initializeListView() {
        String[] sampleCards = new String[]{
                "Card 1", "Card 2", "Card 3", "Card 4", "Card 5",
                "Card 6", "Card 7", "Card 8", "Card 9", "Card 10"
        };

        listViewCards = (ListView) getActivity().findViewById(R.id.cardsListView);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, sampleCards);
        listViewCards.setAdapter(adapter);
        listViewCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Do something e.g. edit card
            }
        });
    }

    private void initializeButton() {
        buttonStartStudy = (Button) getActivity().findViewById(R.id.startStudyButton);
        buttonStartStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_decks_and_cards,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                break;
        }

        return false;
    }


}
