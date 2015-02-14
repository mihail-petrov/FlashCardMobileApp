package com.mentormateacademy.flashcardmobileclient.ui.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;

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
        addLayoutListener();

    }

    private void addLayoutListener() {
        final ViewTreeObserver treeObserver = buttonForgotPassword.getViewTreeObserver();

        final ViewTreeObserver.OnGlobalLayoutListener layoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int currentApiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentApiVersion >= 16) {
                    buttonForgotPassword.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    buttonForgotPassword.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                buttonSignUp.setWidth(buttonForgotPassword.getWidth());
            }
        };
        treeObserver.addOnGlobalLayoutListener(layoutListener);
    }

    private void initializeViewsAndSetListeners() {
        buttonLogIn = (Button) getActivity().findViewById(R.id.logInButton);
        buttonSignUp = (Button) getActivity().findViewById(R.id.signUpButton);
        buttonForgotPassword = (Button) getActivity().findViewById(R.id.forgotPasswordButton);

        buttonLogIn.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
        buttonForgotPassword.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logInButton:

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
}
