package com.mentormateacademy.flashcardmobileclient.ui.fragments.edit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mentormateacademy.flashcardmobileclient.R;

public class NewFrontCardFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_new_card_layout, container, false);

        EditText newCardScreenTitleEditText = (EditText) fragmentView.findViewById(R.id.newCardScreenTitleEditText);
        EditText newCardContentEditText = (EditText) fragmentView.findViewById(R.id.newCardContentEditText);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}