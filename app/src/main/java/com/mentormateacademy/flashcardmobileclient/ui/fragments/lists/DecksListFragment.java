package com.mentormateacademy.flashcardmobileclient.ui.fragments.lists;

import android.app.Activity;
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
import android.widget.ListView;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.data.loaders.cursorObjects.DeckCursorObject;
import com.mentormateacademy.flashcardmobileclient.data.loaders.interfaces.BaseCursorLoader;
import com.mentormateacademy.flashcardmobileclient.data.singletons.DataHolder;
import com.mentormateacademy.flashcardmobileclient.database.helper.DatabaseRepository;
import com.mentormateacademy.flashcardmobileclient.database.repositories.DeckRepository;
import com.mentormateacademy.flashcardmobileclient.models.Deck;

public class DecksListFragment extends Fragment implements AdapterView.OnItemClickListener {

    // Fragment - Properties
    // =======================================================================

    private DeckRepository deckRepository;

    // Fragment - Constructor
    // =======================================================================

    public DecksListFragment(){

    }

    public static DecksListFragment newInstance(){
        DecksListFragment fragmentObject = new DecksListFragment();
        return fragmentObject;
    }

    // Fragment - Life Cycle
    // =======================================================================

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        //
        mActivity = (fragmentActionCallback) activity;

        //
        this.deckRepository = DatabaseRepository.getRepository(getActivity()).getDeckRepository();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_list_deck, container, false);

        long id = DataHolder.getData().getUserId();

        // Get cursor loader
        BaseCursorLoader cursorLoader = new DeckCursorObject(getActivity(), id).getCursorLoader();

        // Get list view
        ListView deckListView = (ListView) fragmentView.findViewById(R.id.deckListView);

        deckListView.setAdapter(cursorLoader.getCursorAdapter());
        deckListView.setOnItemClickListener(this);

        // Loader Manager
        getLoaderManager().initLoader(0, null, cursorLoader);

        return fragmentView;
    }

    // Fragment - Event Handler
    // =======================================================================

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Get Deck arguments
        Bundle arguments = new Bundle();
        arguments.putString("_id", String.valueOf(id));

        // get current deck element
        Deck deck = deckRepository.readBy(arguments).get(0);

        // populate DataHolder object
        DataHolder dataRepository = DataHolder.getData();
        dataRepository.setDeckId(deck.getId());
        dataRepository.setDeckTitle(deck.getTitle());
        dataRepository.setStrategyId(deck.getStrategyId());
        dataRepository.setCorrect(deck.getCorrectAnswers());
        dataRepository.setWrong(deck.getWrongAnswers());
        dataRepository.setCardSize(deck.getCardSize());

        mActivity.onDeckItemSelected();
    }

    // Fragment - Option Menu
    // =======================================================================

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_new_deck, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add_new_deck) {
            mActivity.onDeckItemAdded();
        }

        if(item.getItemId() == R.id.settings) {
            mActivity.onSettingsSelected();
        }

        return super.onOptionsItemSelected(item);
    }


    // Fragment - Interface Callback
    // =======================================================================

    private fragmentActionCallback mActivity;

    public interface fragmentActionCallback {

        /**
         *
         */
        public void onDeckItemAdded();


        /**
         *
         */
        public void onDeckItemSelected();

        /**
         *
         */
        public void onSettingsSelected();
    }
}
