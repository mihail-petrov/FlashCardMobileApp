package com.mentormateacademy.flashcardmobileclient.ui.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mentormateacademy.flashcardmobileclient.R;

public class SignUpFragment extends Fragment implements View.OnClickListener{

    private EditText editTextUsername, editTextPassword, editTextConfirmPassword;
    private Button buttonLogIn, buttonSignUp;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        buttonLogIn = (Button) view.findViewById(R.id.logInButton);
        buttonSignUp = (Button) view.findViewById(R.id.signUpButton);

        buttonLogIn.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signUpButton:

                break;
            case R.id.logInButton:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }

    }
}
