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
import android.widget.RadioGroup;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.database.helper.DatabaseRepository;
import com.mentormateacademy.flashcardmobileclient.models.Deck;

public class NewDeckFragment extends Fragment {

    private onAddNewDeckCallback myActivity;

    public NewDeckFragment() {

    }

    public static NewDeckFragment newInstance() {

        NewDeckFragment fragmentComponent = new NewDeckFragment();
        return fragmentComponent;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        myActivity = (onAddNewDeckCallback) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_new_deck, container, false);

        // Init view component
        final EditText deckTitleEditText = (EditText) fragmentView.findViewById(R.id.deckTitleEditText);
        final RadioGroup deckStrategyRadioGroup = (RadioGroup) fragmentView.findViewById(R.id.deckStrategyRadioGroup);
        Button addNewDeckButton = (Button) fragmentView.findViewById(R.id.addNewDeckButton);


        addNewDeckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // create new deck object
                Deck deckElement = new Deck();
                deckElement.setTitle(deckTitleEditText.getText().toString());

                // get strategy
                int id = deckStrategyRadioGroup.getCheckedRadioButtonId();

                if (id == R.id.spacedRepetition) {
                    deckElement.setStrategyId(1);
                }

                if (id == R.id.shuffleRepetition) {
                    deckElement.setStrategyId(2);
                }

                if (id == R.id.orderProgress) {
                    deckElement.setStrategyId(3);
                }

                // get deck title
                DatabaseRepository.getRepository(getActivity()).getDeckRepository().create(deckElement);

                //
                myActivity.addNewDeck();
            }
        });

        return fragmentView;
    }

    // CALLBACK INTERFACE
    public interface onAddNewDeckCallback {
        public void addNewDeck();
    }
}
