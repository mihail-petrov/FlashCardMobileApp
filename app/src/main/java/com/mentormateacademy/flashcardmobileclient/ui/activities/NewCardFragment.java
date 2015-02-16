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
import android.widget.EditText;

import com.mentormateacademy.flashcardmobileclient.R;

public class NewCardFragment extends Fragment{

    private EditText editTextCardName, editTextNoteContent;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_card, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializeViewsAndSetListeners();

    }

    private void initializeViewsAndSetListeners() {
        editTextCardName = (EditText) getActivity().findViewById(R.id.cardNameEditText);
        editTextNoteContent = (EditText) getActivity().findViewById(R.id.noteContentEditText);

        editTextNoteContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Handle hint location in the edit text
                if(TextUtils.isEmpty(s)){
                    editTextNoteContent.setGravity(Gravity.CENTER);
                }else {
                    editTextNoteContent.setGravity(Gravity.NO_GRAVITY);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
