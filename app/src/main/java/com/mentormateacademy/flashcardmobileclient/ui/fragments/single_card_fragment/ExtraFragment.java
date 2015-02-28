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


public class ExtraFragment extends Fragment {


    public ExtraFragment() {

    }

    public static ExtraFragment newInstance(Bundle arguments) {
        ExtraFragment fragment = new ExtraFragment();
        fragment.setArguments(arguments);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.card_layout, container, false);

        if (getArguments().getString(ExtraFlagsConfiguration.EXTRA_CARD_CONTENT) != null) {
            TextView cardContent = (TextView) fragmentView.findViewById(R.id.cardContent);

            String styledText = getArguments().getString(ExtraFlagsConfiguration.EXTRA_CARD_CONTENT);
            cardContent.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
        }
        else {
            TextView cardContent = (TextView) fragmentView.findViewById(R.id.cardContent);
            cardContent.setText(getActivity().getResources().getString(R.string.extra_card_warning));
        }
        return fragmentView;
    }
}
