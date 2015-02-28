package com.mentormateacademy.flashcardmobileclient.ui.fragments.experimental;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.component.androidComponents.SlidingTabLayout;
import com.mentormateacademy.flashcardmobileclient.component.baseComponents.ContainerFragment;
import com.mentormateacademy.flashcardmobileclient.component.customComponents.editText.RichEditText;
import com.mentormateacademy.flashcardmobileclient.configurations.ExtraFlagsConfiguration;
import com.mentormateacademy.flashcardmobileclient.data.adapters.pager.PagerAdapterForCards;
import com.mentormateacademy.flashcardmobileclient.data.singletons.DataHolder;
import com.mentormateacademy.flashcardmobileclient.database.helper.DatabaseRepository;
import com.mentormateacademy.flashcardmobileclient.models.Card;
import com.mentormateacademy.flashcardmobileclient.models.Deck;


public class AddCardViewFragment extends ContainerFragment implements View.OnClickListener {

    // Fragment - Properties
    // =========================================
    private ViewPager viewPager;
    private Card cardElement;

    // Fragment - Constructor
    // =========================================
    public AddCardViewFragment(){
    }

    public static AddCardViewFragment newInstance(){
        AddCardViewFragment fragment = new AddCardViewFragment();

        return fragment;
    }

    // Fragment - Lifecycle
    // =========================================

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mActivity = (fragmentActionCallback) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cardElement = new Card();
        cardElement.setDeckId(DataHolder.getData().getDeckId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View fragmentView = getFragmentView();

        viewPager = (ViewPager) fragmentView.findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapterForCards(getActivity().getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(3);

        SlidingTabLayout tabs = (SlidingTabLayout) fragmentView.findViewById(R.id.viewPagerTabs);
        tabs.setDistributeEvenly(true);
        tabs.setViewPager(viewPager);

        inflateBottomToolbar(R.layout.toolbar_add_new_card);

        //
        ImageView saveCard = (ImageView) fragmentView.findViewById(R.id.save_card);
        saveCard.setOnClickListener(this);

        return fragmentView;
    }

    // Fragment - Event Handler
    // =======================================

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.save_card) {

            Bundle cardDataFragment = new Bundle();

            boolean isValidCard = false;

            // get all fragment views
            for(int i = 0; i < 3; i++) {

                // get indexed fragment
                Fragment fragment = (Fragment) viewPager.getAdapter().instantiateItem(viewPager, i);

                // get fragment properties
                EditText fragmentTitle          = (EditText) fragment.getView().findViewById(R.id.newCardScreenTitleEditText);
                RichEditText fragmentContent    = (RichEditText) fragment.getView().findViewById(R.id.newCardContentEditText);

                // get fragment property value
                String fragmentTitleData    = fragmentTitle.getText().toString();
                String fragmentContentData  = fragmentContent.getFormattedText();

                // make data content check
                if(i == 0 ) {

                    // make data title check
                    if(fragmentTitleData.isEmpty()) {
                        fragmentTitleData = fragmentTitle.getHint().toString();
                    }

                    if(fragmentContentData.isEmpty()) {
                        fragmentContent.setError("Front Card must have content");
                        fragmentContent.setFocusable(true);
                        viewPager.setCurrentItem(i, true);
                        isValidCard = false;
                        break;
                    }
                    else {
                        isValidCard = true;
                        cardDataFragment.putString(ExtraFlagsConfiguration.FRONT_CARD_TITLE, fragmentTitleData);
                        cardDataFragment.putString(ExtraFlagsConfiguration.FRONT_CARD_CONTENT, fragmentContentData);
                    }
                }

                if(i == 1) {

                    // make data title check
                    if(fragmentTitleData.isEmpty()) {
                        fragmentTitleData = fragmentTitle.getHint().toString();
                    }

                    if(fragmentContentData.isEmpty()) {
                        fragmentContent.setError("Back Card must have content");
                        fragmentContent.setFocusable(true);
                        viewPager.setCurrentItem(i, true);
                        isValidCard = false;
                        break;
                    }
                    else {
                        isValidCard = true;
                        cardDataFragment.putString(ExtraFlagsConfiguration.BACK_CARD_TITLE, fragmentTitleData);
                        cardDataFragment.putString(ExtraFlagsConfiguration.BACK_CARD_CONTENT, fragmentContentData);
                    }
                }

                if(i == 2) {

                    // make data title check

                    if(fragmentTitleData.isEmpty()) {
                        fragmentTitleData = fragmentTitle.getHint().toString();
                    }
                    if(!fragmentContentData.isEmpty()) {
                        cardDataFragment.putString(ExtraFlagsConfiguration.EXTRA_CARD_TITLE, fragmentTitleData);
                        cardDataFragment.putString(ExtraFlagsConfiguration.EXTRA_CARD_CONTENT, fragmentContentData);
                    }
                }
            }

            if(isValidCard) {
                cardElement.setCardContent(cardDataFragment);
                DatabaseRepository.getRepository(getActivity()).getCardRepository().create(cardElement);

                // update deck repository
                Bundle deckArguments = new Bundle();
                deckArguments.putString("_id", String.valueOf(DataHolder.getData().getDeckId()));
                Deck deckElement = DatabaseRepository.getRepository(getActivity()).getDeckRepository().readBy(deckArguments).get(0);
                deckElement.setCardSize(deckElement.getCardSize() + 1);

                DataHolder.getData().setCardSize(DataHolder.getData().getCardSize() + 1);
                DatabaseRepository.getRepository(getActivity()).getDeckRepository().update(deckElement, deckArguments);

                mActivity.onCardSaved();
            }
        }
    }

    // Fragment - Action Callbacks
    // =======================================
    private fragmentActionCallback mActivity;

    public interface fragmentActionCallback {
        public void onCardSaved();
    }
}
