package com.mentormateacademy.flashcardmobileclient.ui.fragments.lists;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.data.loaders.cursorObjects.CardCursorObject;
import com.mentormateacademy.flashcardmobileclient.data.loaders.interfaces.BaseCursorLoader;
import com.mentormateacademy.flashcardmobileclient.data.singletons.DataHolder;

public class CardListFragment extends Fragment implements AdapterView.OnItemClickListener {


    // Fragment - Constructor
    // ========================================
    public CardListFragment() {

    }

    public static CardListFragment newInstance() {

        CardListFragment cardListFragment = new CardListFragment();
        return cardListFragment;
    }


    // Fragment - Life Cycle
    // ========================================

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (fragmentActionCallback) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_list_cards, container, false);

        long id = DataHolder.getData().getDeckId();

        // Get Cursor Loader
        BaseCursorLoader cursorLoader = new CardCursorObject(getActivity(), id).getCursorLoader();

        // get list component
        ListView cardListView = (ListView) fragmentView.findViewById(R.id.cardsListView);
        cardListView.setAdapter(cursorLoader.getCursorAdapter());
        cardListView.setOnItemClickListener(this);

        //
        getLoaderManager().initLoader(0, null, cursorLoader);

        return fragmentView;
    }

    // Fragment - Event Handler
    // ========================================

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        DataHolder.getData().setCardId(id);
        mActivity.onCardItemSelected();
    }

    // Fragment - Interface Callback
    // ========================================

    private fragmentActionCallback mActivity;

    public interface fragmentActionCallback {

        /**
         *
         */
        public void onCardItemSelected();
    }
}
