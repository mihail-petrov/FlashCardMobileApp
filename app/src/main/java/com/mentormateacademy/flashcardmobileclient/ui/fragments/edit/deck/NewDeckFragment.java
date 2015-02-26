package com.mentormateacademy.flashcardmobileclient.ui.fragments.edit.deck;

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
import android.widget.EditText;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.component.customComponents.extendRadioGroup.ExtendRadioGroup;
import com.mentormateacademy.flashcardmobileclient.configurations.StrategyConfiguration;
import com.mentormateacademy.flashcardmobileclient.data.singletons.DataHolder;
import com.mentormateacademy.flashcardmobileclient.database.helper.DatabaseRepository;
import com.mentormateacademy.flashcardmobileclient.database.repositories.DeckRepository;
import com.mentormateacademy.flashcardmobileclient.models.Deck;

public class NewDeckFragment extends Fragment
        implements ExtendRadioGroup.ClickOnRadioButton {

    // Components
    private EditText deckTitleEditText;
    private ExtendRadioGroup deckStrategyRadioGroup;

    // Fragment properties
    private fragmentActionCallback myActivity;
    private int strategyId;

    // database elements
    private DeckRepository deckRepository;

    // Constructors
    // ========================================
    public NewDeckFragment() {
        this.strategyId = StrategyConfiguration.ORDER_PROGRESS;
    }

    public static NewDeckFragment newInstance() {
        NewDeckFragment fragmentComponent = new NewDeckFragment();
        return fragmentComponent;
    }

    // Fragment - Life Cycle
    // ========================================
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myActivity = (fragmentActionCallback) activity;

        // get deck title
        this.deckRepository = DatabaseRepository.getRepository(getActivity()).getDeckRepository();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //fragmentView.
        View fragmentView = inflater.inflate(R.layout.fragment_new_deck, container, false);

        deckTitleEditText = (EditText) fragmentView.findViewById(R.id.deckTitleEditText);

        //
        deckStrategyRadioGroup = (ExtendRadioGroup) fragmentView.findViewById(R.id.deckStrategyRadioGroup);
        deckStrategyRadioGroup.setOnRadioButtonSelection(this);

        return fragmentView;
    }

    private void saveDeckObject() {

        String deckTitle = deckTitleEditText.getText().toString().trim();

        if(deckTitle.isEmpty()) {
            deckTitleEditText.setError("Deck name cannot be empty");
        }
        else {
            // create new deck object
            Deck deckElement = new Deck();
            deckElement.setTitle(deckTitle);
            deckElement.setStrategyId(strategyId);
            deckElement.setUserId(DataHolder.getData().getUserId());

            this.deckRepository.create(deckElement);
            myActivity.addNewDeck();
        }
    }


    // Fragment - Menu
    // ========================================
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit_deck, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.save) {
            saveDeckObject();
        }

        return super.onOptionsItemSelected(item);
    }


    // Event Handler
    // ========================================

    @Override
    public void selectRadioButton(View checkedView) {

        if (checkedView.getId() == R.id.orderProgress) {
            strategyId = StrategyConfiguration.ORDER_PROGRESS;
        }

        if (checkedView.getId() == R.id.spacedRepetition) {
            strategyId = StrategyConfiguration.SPACED_REPETITION;
        }

        if (checkedView.getId() == R.id.shuffleRepetition) {
            strategyId = StrategyConfiguration.SHUFFLE_ORDER;
        }
    }

    // Callback Interface
    // ========================================
    public interface fragmentActionCallback {
        public void addNewDeck();
    }
}
