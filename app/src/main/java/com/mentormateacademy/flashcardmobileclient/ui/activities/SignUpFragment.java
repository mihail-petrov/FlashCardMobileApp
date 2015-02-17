package com.mentormateacademy.flashcardmobileclient.ui.activities;


import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import com.mentormateacademy.flashcardmobileclient.R;

public class SignUpFragment extends Fragment implements View.OnClickListener{

    private EditText editTextUsername, editTextPassword, editTextConfirmPassword;
    private Button buttonLogIn, buttonSignUp;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeViews();
    }

    private void initializeViews() {
        buttonLogIn = (Button) getActivity().findViewById(R.id.logInButton);
        buttonSignUp = (Button) getActivity().findViewById(R.id.signUpButton);

        buttonLogIn.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);

        editTextUsername = (EditText) getActivity().findViewById(R.id.usernameSignUpEditText);
        editTextPassword = (EditText) getActivity().findViewById(R.id.passwordSignUpEditText);
        editTextConfirmPassword = (EditText) getActivity().findViewById(R.id.confirmPasswordSignUpEditText);

    }

    @Override
    public void onClick(View v) {
        hideKeyboard();
        switch (v.getId()){
            case R.id.signUpButton:

                break;
            case R.id.logInButton:
                getActivity().getSupportFragmentManager().popBackStack();
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
