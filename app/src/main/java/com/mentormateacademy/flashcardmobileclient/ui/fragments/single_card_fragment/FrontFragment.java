package com.mentormateacademy.flashcardmobileclient.ui.fragments.single_card_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.configurations.ExtraFlagsConfiguration;


public class FrontFragment extends Fragment {

    public FrontFragment() {
    }

    public static FrontFragment newInstance(Bundle arguments) {
        FrontFragment fragment = new FrontFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.card_layout, container, false);

        //
        if (getArguments() != null) {
            TextView cardContent = (TextView) fragmentView.findViewById(R.id.cardContent);

            String styledText = getArguments().getString(ExtraFlagsConfiguration.FRONT_CARD_CONTENT);
            cardContent.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
        }
        return fragmentView;
    }
}
