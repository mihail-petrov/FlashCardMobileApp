package com.mentormateacademy.flashcardmobileclient.component.baseComponents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.mentormateacademy.flashcardmobileclient.R;


public class ContainerFragment extends Fragment {

    private View fragmentView;
    private View bottomToolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragmentView = inflater.inflate(R.layout.example_main_fragment, container, false);
        return this.fragmentView;
    }

    public View getFragmentView(){
        return this.fragmentView;
    }


    public void inflateBottomToolbar(int toolbarId){

        ViewStub fragmentStub = (ViewStub) getFragmentView().findViewById(R.id.layout_stub);
        fragmentStub.setLayoutResource(toolbarId);
        bottomToolbar = fragmentStub.inflate();
    }

    public View getBottomToolbar(){
        return this.bottomToolbar;
    }
}

