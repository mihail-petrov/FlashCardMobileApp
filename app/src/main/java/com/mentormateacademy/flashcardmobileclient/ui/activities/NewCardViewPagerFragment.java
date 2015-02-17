package com.mentormateacademy.flashcardmobileclient.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.mentormateacademy.flashcardmobileclient.R;

public class NewCardViewPagerFragment extends Fragment{

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    private boolean[] editTextsHaveText = new boolean[3];


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_car_view_pager, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewPager = (ViewPager) getActivity().findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        getActivity().setTitle(getActivity().getString(R.string.add_new_card_title));


    }
    public void setEditTextsHaveText(int index, boolean value){
        editTextsHaveText[index] = value;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_save_card, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_new_card_item:
                if(editTextsHaveText[0] && editTextsHaveText[1]){
                    //Save card
                }else{
                    Toast.makeText(getActivity(), getActivity().getString(R.string.not_enough_info_toast_text), Toast.LENGTH_LONG).show();
                }
                return true;

        }

        return false;
    }
}
