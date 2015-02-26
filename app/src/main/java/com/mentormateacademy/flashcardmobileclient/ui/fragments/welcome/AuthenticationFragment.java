package com.mentormateacademy.flashcardmobileclient.ui.fragments.welcome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.data.singletons.DataHolder;
import com.mentormateacademy.flashcardmobileclient.ui.activities.DeckListActivity;

public class AuthenticationFragment extends Fragment implements View.OnClickListener {

    public AuthenticationFragment(){}

    public static AuthenticationFragment newInstance(){
        AuthenticationFragment fragment = new AuthenticationFragment();
        return fragment;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mActivity = (setOnAuthenticationListener) activity;

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_DATA", Context.MODE_PRIVATE);
        boolean isUserLoggedIn = sharedPreferences.getBoolean("IS_USER_LOGGED_IN", false);

        if(isUserLoggedIn) {

            // get user profile
            DataHolder.getData().setUserId(sharedPreferences.getLong("LOGGED_USER_ID", 0));
            // get deck profile
            Intent openActivity = new Intent(getActivity(), DeckListActivity.class);
            startActivity(openActivity);
        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_authentication, container, false);

        //
        LinearLayout loginButton        = (LinearLayout) fragmentView.findViewById(R.id.loginButton);
        LinearLayout registrationButton = (LinearLayout) fragmentView.findViewById(R.id.registrationButton);

        //
        loginButton.setOnClickListener(this);
        registrationButton.setOnClickListener(this);

        return fragmentView;
    }


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.loginButton) {
            mActivity.onLoginButton();
        }

        if(v.getId() == R.id.registrationButton) {
            mActivity.onRegistrationButton();
        }
    }

    private setOnAuthenticationListener mActivity;

    public interface setOnAuthenticationListener{

        public void onLoginButton();

        public void onRegistrationButton();
    }
}
