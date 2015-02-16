package com.mentormateacademy.flashcardmobileclient.ui.activities;


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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mentormateacademy.flashcardmobileclient.R;

public class LogInFragment extends Fragment implements View.OnClickListener{

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogIn, buttonForgotPassword, buttonSignUp;
    private LinearLayout linearLayoutLogIn;
    private RelativeLayout relativeLayoutLogIn;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializeViewsAndSetListeners();

        setObserverForLayout();

    }


    private void setObserverForLayout() {
        final ViewTreeObserver treeObserver = linearLayoutLogIn.getViewTreeObserver();

        final ViewTreeObserver.OnGlobalLayoutListener layoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int currentApiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentApiVersion >= 16) {
                    linearLayoutLogIn.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    linearLayoutLogIn.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int width=displayMetrics.widthPixels;
                int height=displayMetrics.heightPixels;
                int dens=displayMetrics.densityDpi;
                double wi=(double)width/(double)dens;
                double he=(double)height/(double)dens;
                double x = Math.pow(wi,2);
                double y = Math.pow(he, 2);
                double diagonalInches = Math.sqrt(x+y);

                final double coefficient = diagonalInches / MainActivity.BASE_INCHES;


                if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){

                    adjustViews(coefficient, displayMetrics.density);

                    buttonSignUp.setWidth((int) (buttonForgotPassword.getWidth() * coefficient));
                    buttonForgotPassword.setWidth((int) (buttonForgotPassword.getWidth() * coefficient));
                }else {

                    adjustViews(coefficient, displayMetrics.density);

                    buttonSignUp.setWidth((int) (buttonForgotPassword.getWidth() * ((float) width / (float) height) * coefficient));
                    buttonForgotPassword.setWidth((int) (buttonForgotPassword.getWidth() * ((float) width / (float) height) * coefficient));

                }

            }
        };

        treeObserver.addOnGlobalLayoutListener(layoutListener);

    }

    //Adjust Views based on the screen size
    private void adjustViews(double coefficient, float density) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, getResources().getDisplayMetrics());

        //params = (LinearLayout.LayoutParams) editTextUsername.getLayoutParams();
        params.setMargins(0, (int) (50*coefficient*density),0,0);
        //editTextUsername.setLayoutParams(params);
        editTextUsername.setTextSize(TypedValue.COMPLEX_UNIT_PX,(float) (editTextUsername.getTextSize()*coefficient));

        params.setMargins(0, (int) (50*coefficient*density),0,0);
        //editTextPassword.setLayoutParams(params);
        editTextPassword.setTextSize(TypedValue.COMPLEX_UNIT_PX,(float) (editTextPassword.getTextSize()*coefficient));

        params.setMargins((int) (15 * coefficient), (int) (75 * coefficient),
                (int) (15 * coefficient), (int) (25 * coefficient));
        //buttonLogIn.setLayoutParams(params);
        buttonLogIn.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (buttonLogIn.getTextSize() * coefficient));

        params.setMargins((int) (15 * coefficient), 0, (int) (15 * coefficient), 0);
        //relativeLayoutLogIn.setLayoutParams(params);

        buttonSignUp.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (buttonSignUp.getTextSize() * coefficient));
        buttonForgotPassword.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (buttonForgotPassword.getTextSize() * coefficient));
    }

    private void initializeViewsAndSetListeners() {
        buttonLogIn = (Button) getActivity().findViewById(R.id.logInButton);
        buttonSignUp = (Button) getActivity().findViewById(R.id.signUpButton);
        buttonForgotPassword = (Button) getActivity().findViewById(R.id.forgotPasswordButton);

        buttonLogIn.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
        buttonForgotPassword.setOnClickListener(this);

        linearLayoutLogIn = (LinearLayout) getActivity().findViewById(R.id.logInFragmentLayout);
        relativeLayoutLogIn = (RelativeLayout) getActivity().findViewById(R.id.logInFragmentRelativeLayout);

        editTextUsername = (EditText) getActivity().findViewById(R.id.usernameLogInEditText);
        editTextPassword = (EditText) getActivity().findViewById(R.id.passwordLogInEditText);

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
