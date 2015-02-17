package com.mentormateacademy.flashcardmobileclient.ui.activities;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mentormateacademy.flashcardmobileclient.R;

public class LogInFragment extends Fragment implements View.OnClickListener{

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogIn, buttonForgotPassword, buttonSignUp;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializeViewsAndSetListeners();

    }

    private void initializeViewsAndSetListeners() {
        buttonLogIn = (Button) getActivity().findViewById(R.id.logInButton);
        buttonSignUp = (Button) getActivity().findViewById(R.id.signUpButton);
        buttonForgotPassword = (Button) getActivity().findViewById(R.id.forgotPasswordButton);

        buttonLogIn.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
        buttonForgotPassword.setOnClickListener(this);

        editTextUsername = (EditText) getActivity().findViewById(R.id.usernameLogInEditText);
        editTextPassword = (EditText) getActivity().findViewById(R.id.passwordLogInEditText);

    }


    @Override
    public void onClick(View v) {
        hideKeyboard();
        switch (v.getId()){
            case R.id.logInButton:
                Intent newIntent = new Intent(getActivity(), ManageFlashCardsActivity.class);
                startActivity(newIntent);
                getActivity().finish();
                break;
            case R.id.signUpButton:
                SignUpFragment signUpFragment = new SignUpFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, signUpFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
                break;
            case R.id.forgotPasswordButton:

                break;
        }
    }
    private void hideKeyboard() {
        // Check if no view has focus:
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
