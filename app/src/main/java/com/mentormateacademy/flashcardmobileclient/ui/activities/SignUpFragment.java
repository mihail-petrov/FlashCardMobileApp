package com.mentormateacademy.flashcardmobileclient.ui.activities;


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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mentormateacademy.flashcardmobileclient.R;

public class SignUpFragment extends Fragment implements View.OnClickListener{

    private EditText editTextUsername, editTextPassword, editTextConfirmPassword;
    private Button buttonLogIn, buttonSignUp;
    private LinearLayout linearLayoutSignUp;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeViews();
        setObserverForLayout();
    }

    private void initializeViews() {
        buttonLogIn = (Button) getActivity().findViewById(R.id.logInButton);
        buttonSignUp = (Button) getActivity().findViewById(R.id.signUpButton);

        buttonLogIn.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);

        editTextUsername = (EditText) getActivity().findViewById(R.id.usernameSignUpEditText);
        editTextPassword = (EditText) getActivity().findViewById(R.id.passwordSignUpEditText);
        editTextConfirmPassword = (EditText) getActivity().findViewById(R.id.confirmPasswordSignUpEditText);

        linearLayoutSignUp = (LinearLayout) getActivity().findViewById(R.id.signUpLinearLayout);
    }

    private void setObserverForLayout() {

        final ViewTreeObserver treeObserver = linearLayoutSignUp.getViewTreeObserver();

        final ViewTreeObserver.OnGlobalLayoutListener layoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int currentApiVersion = Build.VERSION.SDK_INT;
                if (currentApiVersion >= 16) {
                    linearLayoutSignUp.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    linearLayoutSignUp.getViewTreeObserver().removeGlobalOnLayoutListener(this);
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
                adjustViews(coefficient, displayMetrics.density);
            }
        };

        treeObserver.addOnGlobalLayoutListener(layoutListener);

    }

    //Adjust Views based on the screen size
    private void adjustViews(double coefficient, float density) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        //float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, getResources().getDisplayMetrics());

        //params = (LinearLayout.LayoutParams) editTextUsername.getLayoutParams();
        params.setMargins(0, (int) (30*coefficient*density),0,0);
        //editTextUsername.setLayoutParams(params);
        editTextUsername.setTextSize(TypedValue.COMPLEX_UNIT_PX,(float) (editTextUsername.getTextSize()*coefficient));

        params.setMargins(0, (int) (30*coefficient*density),0,0);
        //editTextPassword.setLayoutParams(params);
        editTextPassword.setTextSize(TypedValue.COMPLEX_UNIT_PX,(float) (editTextPassword.getTextSize()*coefficient));

        params.setMargins(0, (int) (30*coefficient*density),0,0);
        //editTextPassword.setLayoutParams(params);
        editTextConfirmPassword.setTextSize(TypedValue.COMPLEX_UNIT_PX,(float) (editTextConfirmPassword.getTextSize()*coefficient));

        params.setMargins((int) (15 * coefficient), (int) (50 * coefficient), (int) (15 * coefficient), 0);
        //relativeLayoutLogIn.setLayoutParams(params);
        buttonSignUp.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (buttonSignUp.getTextSize() * coefficient));

        params.setMargins((int) (15 * coefficient), (int) (25 * coefficient),
                (int) (15 * coefficient), 0);
        //buttonLogIn.setLayoutParams(params);
        buttonLogIn.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (buttonLogIn.getTextSize() * coefficient));


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
