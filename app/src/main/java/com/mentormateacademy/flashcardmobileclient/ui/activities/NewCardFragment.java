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
import android.widget.TextView;

import com.mentormateacademy.flashcardmobileclient.R;

public class NewCardFragment extends Fragment{

    private EditText editTextCardName, editTextNoteContent;
    private TextView textView;

    private boolean editTextCardNameHasText, editTextNoteContentHasText;
    private int index;

    private static final String SAVED_INDEX = "savedIndex";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        index = this.getArguments().getInt(ViewPagerAdapter.INDEX_KEY);

        if(savedInstanceState != null){
            index = savedInstanceState.getInt(SAVED_INDEX);
        }
        String layoutId = "fragment_new_card_" + index;
        int id = getActivity().getResources().getIdentifier(layoutId, "layout", getActivity().getApplicationContext().getPackageName());
        return inflater.inflate(id, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null){
            index = savedInstanceState.getInt(SAVED_INDEX);
        }

        editTextCardNameHasText = false;
        editTextNoteContentHasText = false;

        try {
            initializeViewsAndSetListeners();
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(SAVED_INDEX, index);
        super.onSaveInstanceState(outState);
    }

    private void initializeViewsAndSetListeners() {
        int idTextView = getActivity().getResources().getIdentifier("textView"+index, "id", getActivity().getApplicationContext().getPackageName());
        textView = (TextView) getActivity().findViewById(idTextView);
        textView.setText("Side " + index);

        int idCardName = getActivity().getResources().getIdentifier("cardNameEditText"+index, "id", getActivity().getApplicationContext().getPackageName());
        editTextCardName = (EditText) getActivity().findViewById(idCardName);

        int idNoteContent = getActivity().getResources().getIdentifier("noteContentEditText"+index, "id", getActivity().getApplicationContext().getPackageName());
        editTextNoteContent = (EditText) getActivity().findViewById(idNoteContent);

        editTextNoteContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Handle hint location in the edit text
                //Check if the editText is empty
                if(TextUtils.isEmpty(s)){
                    editTextNoteContent.setGravity(Gravity.CENTER);
                    editTextNoteContentHasText = false;
                }else {
                    editTextNoteContent.setGravity(Gravity.NO_GRAVITY);
                    editTextNoteContentHasText = true;
                }
                NewCardViewPagerFragment newCardViewPagerFragment = (NewCardViewPagerFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.container);
                newCardViewPagerFragment.setEditTextsHaveText(index-1,editTextCardNameHasText && editTextNoteContentHasText);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextCardName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Check if the editText is empty
                if(TextUtils.isEmpty(s)){
                    editTextCardNameHasText = false;
                }else {
                    editTextCardNameHasText = true;
                }

                NewCardViewPagerFragment newCardViewPagerFragment = (NewCardViewPagerFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.container);
                newCardViewPagerFragment.setEditTextsHaveText(index - 1,editTextCardNameHasText && editTextNoteContentHasText);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
