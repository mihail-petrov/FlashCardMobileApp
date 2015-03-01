package com.mentormateacademy.flashcardmobileclient.ui.fragments.welcome;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.data.singletons.DataHolder;
import com.mentormateacademy.flashcardmobileclient.database.helper.DatabaseRepository;
import com.mentormateacademy.flashcardmobileclient.helpers.ValidationHelper;
import com.mentormateacademy.flashcardmobileclient.models.User;

import java.util.ArrayList;

public class LoginFragment
            extends
                Fragment
            implements
                View.OnClickListener {

    // Fragment - Properties
    // ===============================================
    private EditText userEmailEditText;
    private EditText userPasswordEditText;

    private Bundle userRegistration;

    // Fragment - Constructor
    // ===============================================

    public LoginFragment(){}

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }


    // Fragment - Life Cycle
    // ===============================================

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (fragmentActionCallback) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userRegistration = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_login, container, false);

        userEmailEditText      = (EditText) fragmentView.findViewById(R.id.userEmailEditText);
        userPasswordEditText   = (EditText) fragmentView.findViewById(R.id.userPasswordEditText);

        LinearLayout loginButton        = (LinearLayout) fragmentView.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        return fragmentView;
    }

    // Fragment - Methods
    // ===============================================
    public void login(){

        //
        boolean isEmailValid = false;
        boolean isPassValid  = false;

        // get field credentials
        String userEmail = userEmailEditText.getText().toString();
        String userPass  = userPasswordEditText.getText().toString();

        // check if E-mail is valid
        if(!userEmail.isEmpty() && ValidationHelper.isEmailValid(userEmail)) {
            userRegistration.putString("email", userEmail);
            isEmailValid = true;
        }
        else {
            userEmailEditText.setError(getActivity().getResources().getString(R.string.validation_email));
            isEmailValid = false;
        }

        // check if password is valid
        if(!userPass.isEmpty()) {
            userRegistration.putString("pass", userPass);
            isPassValid = true;
        }
        else {
            userPasswordEditText.setError(getActivity().getResources().getString(R.string.validation_password));
            isPassValid = false;
        }

        if(isEmailValid && isPassValid) {

            // check if the E-mail already exists
            Bundle checkUserProfile = new Bundle();
            checkUserProfile.putString("email", userEmail);
            checkUserProfile.putString("password", userPass);

            ArrayList<User> users = DatabaseRepository.getRepository(getActivity())
                    .getUserRepository().readBy(checkUserProfile);

            if(users.size() == 1) {
                // get user
                User user = users.get(0);
                long userId = user.getId();
                DataHolder.getData().setUserId(userId);

                // check activity profile to shared preference
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_DATA", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("IS_USER_LOGGED_IN", true);
                editor.putLong("LOGGED_USER_ID", userId);
                editor.apply();

                //
                mActivity.onLoginSuccess();
            }
            else {
                userEmailEditText.setError(getActivity().getResources().getString(R.string.validation_user_existence));
            }
        }
    }

    private fragmentActionCallback mActivity;

    public interface fragmentActionCallback {
        public void onLoginSuccess();
    }

    @Override
    public void onClick(View v) {
        login();
    }
}
