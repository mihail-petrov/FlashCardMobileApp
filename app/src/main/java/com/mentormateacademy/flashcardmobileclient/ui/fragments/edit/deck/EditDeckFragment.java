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

public class EditDeckFragment extends Fragment
        implements ExtendRadioGroup.ClickOnRadioButton {

    // Components
    private EditText deckTitleEditText;
    private ExtendRadioGroup deckStrategyRadioGroup;

    // Fragment properties
    private fragmentActionCallback myActivity;
    private int strategyId;

    // Database element
    private Deck deckElement;
    private DeckRepository deckRepository;


    // Constructors
    // ========================================
    public EditDeckFragment() {

    }

    public static EditDeckFragment newInstance() {
        EditDeckFragment fragment = new EditDeckFragment();
        return fragment;
    }


    // Fragment - Life Cycle
    // ========================================

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myActivity = (fragmentActionCallback) activity;

        //
        Bundle deckArg = new Bundle();
        deckArg.putString("_id", String.valueOf(DataHolder.getData().getDeckId()));

        // get Database objects
        deckRepository = DatabaseRepository.getRepository(getActivity()).getDeckRepository();
        deckElement    = deckRepository.readBy(deckArg).get(0);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_new_deck, container, false);

        // init fragment components
        deckTitleEditText = (EditText) fragmentView.findViewById(R.id.deckTitleEditText);
        deckTitleEditText.setText(deckElement.getTitle());

        //
        deckStrategyRadioGroup = (ExtendRadioGroup) fragmentView.findViewById(R.id.deckStrategyRadioGroup);
        deckStrategyRadioGroup.setOnRadioButtonSelection(this);
        setInitStrategyId(deckElement.getStrategyId());

        return fragmentView;
    }

    // Fragment - Methods
    // ========================================

    public void updateDeckObject(){

        String deckTitle = deckTitleEditText.getText().toString().trim();

        if(deckTitle.isEmpty()) {
            deckTitleEditText.setError("Deck name cannot be empty");
        }
        else {
            Bundle deckArg = new Bundle();
            deckArg.putString("_id", String.valueOf(DataHolder.getData().getDeckId()));

            // set global data
            DataHolder.getData().setDeckTitle(deckTitle);

            // set deck element
            deckElement.setTitle(deckTitle);
            deckElement.setStrategyId(strategyId);

            deckRepository.update(deckElement, deckArg);
            myActivity.onDeckItemEdit();
        }
    }

    // Fragment - Event Handlers
    // ========================================

    public void setInitStrategyId(int strategyId){

        if(strategyId == StrategyConfiguration.SHUFFLE_ORDER) {
            deckStrategyRadioGroup.selectRadioButton(R.id.shuffleRepetition);
        }

        if(strategyId == StrategyConfiguration.SPACED_REPETITION) {
            deckStrategyRadioGroup.selectRadioButton(R.id.spacedRepetition);
        }

        if(strategyId == StrategyConfiguration.ORDER_PROGRESS) {
            deckStrategyRadioGroup.selectRadioButton(R.id.orderProgress);
        }
    }

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

    // Fragment - Menu
    // ========================================

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_edit_deck, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.save) {
            updateDeckObject();
        }

        return super.onOptionsItemSelected(item);
    }


    // Callback Interface
    // ========================================
    public interface fragmentActionCallback {
        public void onDeckItemEdit();
    }
}
