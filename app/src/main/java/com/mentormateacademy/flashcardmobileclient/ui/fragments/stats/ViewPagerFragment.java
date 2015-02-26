package com.mentormateacademy.flashcardmobileclient.ui.fragments.stats;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.component.androidComponents.SlidingTabLayout;
import com.mentormateacademy.flashcardmobileclient.data.adapters.pager.StatPagerAdapter;
import com.mentormateacademy.flashcardmobileclient.data.singletons.DataHolder;


public class ViewPagerFragment extends Fragment {

    // Fragment - Properties
    // ====================================

    private int correct;
    private int wrong;

    // Fragment - Constructor
    // ====================================

    public ViewPagerFragment(){

    }

    public static ViewPagerFragment newInstance(){
        ViewPagerFragment fragmentObject = new ViewPagerFragment();
        return fragmentObject;
    }

    // Fragment - Life Cycle
    // ====================================

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        this.correct = DataHolder.getData().getCorrect();
        this.wrong   = DataHolder.getData().getWrong();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.view_pager_layout, container, false);


        ViewPager viewPager = (ViewPager) fragmentView.findViewById(R.id.viewPager);
        viewPager.setAdapter(new StatPagerAdapter(getActivity().getSupportFragmentManager(), this.correct, this.wrong));
        SlidingTabLayout tabs = (SlidingTabLayout) fragmentView.findViewById(R.id.viewPagerTabs);
        tabs.setDistributeEvenly(true);
        tabs.setViewPager(viewPager);


        return fragmentView;
    }
}
