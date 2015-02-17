package com.mentormateacademy.flashcardmobileclient.ui.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mentormateacademy.flashcardmobileclient.R;


public class DeckFragment extends Fragment{

    private ListView listViewDecks;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_decks, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializeListView();
        getActivity().setTitle(getActivity().getString(R.string.your_decks_activity_title));
    }

    private void initializeListView() {
        final String[] sampleDecks = new String[]{
                "Deck 1", "Deck 2", "Deck 3", "Deck 4", "Deck 5",
                "Deck 6", "Deck 7", "Deck 8", "Deck 9", "Deck 10"
        };

        listViewDecks = (ListView) getActivity().findViewById(R.id.deckListView);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, sampleDecks);
        listViewDecks.setAdapter(adapter);
        listViewDecks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Add fragment containing the cards in the deck
                CardsFragment cardsFragment = new CardsFragment();
                Bundle args = new Bundle();
                args.putString(ManageFlashCardsActivity.SELECTED_DECK_NAME, sampleDecks[position]);
                cardsFragment.setArguments(args);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, cardsFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
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
            case R.id.crete_new_menu_item:
                NewDeckFragment newDeckFragment = new NewDeckFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, newDeckFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
                return true;

        }

        return false;
    }
}