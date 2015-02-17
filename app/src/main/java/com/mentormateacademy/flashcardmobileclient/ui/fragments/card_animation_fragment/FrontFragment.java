package com.mentormateacademy.flashcardmobileclient.ui.fragments.card_animation_fragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mentormateacademy.flashcardmobileclient.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FrontFragment extends Fragment {

    public FrontFragment() {
    }

    public static FrontFragment newInstance(String cardTitle, String cardContent) {

        // create bundle
        Bundle arguments = new Bundle();
        arguments.putString("card_title", cardTitle);
        arguments.putString("card_content", cardContent);

        // create fragment
        FrontFragment fragment = new FrontFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        Bundle arguments = getArguments();

        // TODO: Remove clean context
        View fragmentView = inflater.inflate(R.layout.front_layout, container, false);

//        TextView cardTitle = (TextView) fragmentView.findViewById(R.id.cardTitle);
        TextView cardContent = (TextView) fragmentView.findViewById(R.id.cardContent);

        //
        if (arguments != null) {

//            cardTitle.setText(getArguments().getString("card_title"));
            cardContent.setText(getArguments().getString("card_content"));
        }
        return fragmentView;
    }
}
