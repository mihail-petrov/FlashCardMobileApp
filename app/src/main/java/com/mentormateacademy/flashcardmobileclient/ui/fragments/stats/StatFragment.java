package com.mentormateacademy.flashcardmobileclient.ui.fragments.stats;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mentormateacademy.flashcardmobileclient.component.customComponents.canvasView.CanvasView;


public class StatFragment extends Fragment {

    // Fragment - Constructors
    // ====================================

    public StatFragment(){

    }


    public static StatFragment newInstance(int correct, int wrong, String color){

        Bundle arguments = new Bundle();
        arguments.putInt("CORRECT", correct);
        arguments.putInt("WRONG", wrong);
        arguments.putString("COLOR", color);

        StatFragment fragment = new StatFragment();
        fragment.setArguments(arguments);

        return fragment;
    }


    // Fragment - Life Cycle
    // ====================================

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int correctA    = getArguments().getInt("CORRECT", 0);
        int wrongA      = getArguments().getInt("WRONG", 0);
        int totalC      = (correctA + wrongA);

        CanvasView canvasView = new CanvasView(getActivity(), correctA, totalC, true);
        canvasView.setColor(getArguments().getString("COLOR"));

        return canvasView;
    }
}
