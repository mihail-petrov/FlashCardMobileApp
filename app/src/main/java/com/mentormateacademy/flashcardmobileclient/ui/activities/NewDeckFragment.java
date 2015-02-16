package com.mentormateacademy.flashcardmobileclient.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.mentormateacademy.flashcardmobileclient.R;

public class NewDeckFragment extends Fragment {

    private EditText editTextDeckName, editTextDeckSubject, editTextDeckDescription;
    private Button buttonCreateDeck;
    private Spinner spinnerCombo;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_deck, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle(getActivity().getString(R.string.create_new_deck_title));
        initializeViews();
        setListeners();
    }

    private void initializeViews() {
        editTextDeckName = (EditText) getActivity().findViewById(R.id.deckNameEditText);
        editTextDeckSubject = (EditText) getActivity().findViewById(R.id.deckSubjectEditText);
        editTextDeckDescription = (EditText) getActivity().findViewById(R.id.deckDescriptionEditText);

        buttonCreateDeck = (Button) getActivity().findViewById(R.id.createDeckButton);

        spinnerCombo = (Spinner) getActivity().findViewById(R.id.comboSpinner);

    }

    private void setListeners() {

        editTextDeckDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Handle hint location in the edit text
                if(TextUtils.isEmpty(s)){
                    editTextDeckDescription.setGravity(Gravity.CENTER);
                }else {
                    editTextDeckDescription.setGravity(Gravity.NO_GRAVITY);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        buttonCreateDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        String[] sampleSpinnerList = new String[]{
                "1","2","3","4", "5"
        };

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,sampleSpinnerList);
        spinnerCombo.setAdapter(adapter);
        spinnerCombo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Do something
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
