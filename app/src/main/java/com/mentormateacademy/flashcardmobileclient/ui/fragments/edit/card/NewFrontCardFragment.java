package com.mentormateacademy.flashcardmobileclient.ui.fragments.edit.card;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.component.customComponents.editText.RichEditText;
import com.mentormateacademy.flashcardmobileclient.configurations.ExtraFlagsConfiguration;

public class NewFrontCardFragment
        extends
            Fragment
{

    // Components
    private EditText fragmentTitle;
    private RichEditText fragmentContent;

    // CONSTRUCTORS
    // =============================================

    public NewFrontCardFragment() {}

    public static NewFrontCardFragment newInstance(Bundle arguments) {

        NewFrontCardFragment fragment = new NewFrontCardFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    // Fragment - Life Cycle
    // ===================================

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_new_card_front_layout, container, false);

        if(getArguments() != null) {

            // Get Front title
            fragmentTitle = (EditText) fragmentView.findViewById(R.id.newCardScreenTitleEditText);
            fragmentTitle.setText(getArguments().getString(ExtraFlagsConfiguration.FRONT_CARD_TITLE));

            // get Front Content
            fragmentContent = (RichEditText) fragmentView.findViewById(R.id.newCardContentEditText);
            fragmentContent.setText(getArguments().getString(ExtraFlagsConfiguration.FRONT_CARD_CONTENT));
        }
        else  {

            // Get Front title
            fragmentTitle = (EditText) fragmentView.findViewById(R.id.newCardScreenTitleEditText);

            // get Front Content
            fragmentContent = (RichEditText) fragmentView.findViewById(R.id.newCardContentEditText);
        }

        return fragmentView;
    }
}
