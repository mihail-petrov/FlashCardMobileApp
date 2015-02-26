package com.mentormateacademy.flashcardmobileclient.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.component.baseComponents.BaseActivity;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.welcome.AuthenticationFragment;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.welcome.LoginFragment;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.welcome.RegistrationFragment;


public class WelcomeActivity extends BaseActivity
        implements
            AuthenticationFragment.setOnAuthenticationListener,
            RegistrationFragment.fragmentActionCallback,
        LoginFragment.fragmentActionCallback

{

    //
    // ========================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initAuthenticationFragment();
    }

    public void initAuthenticationFragment() {
        setToolbarTitle("Flash Card App");
        setFragment(AuthenticationFragment.newInstance(), false);
    }

    public void initLoginFragment() {
        setToolbarTitle("Log in");
        setFragment(LoginFragment.newInstance(), false);
    }

    public void initRegistrationFragment() {
        setToolbarTitle("New User");
        setFragment(RegistrationFragment.newInstance(), false);
    }


    //
    // ========================================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_authentication, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.login) {
            initLoginFragment();
        }

        if(item.getItemId() == R.id.registration) {
            initRegistrationFragment();
        }

        return super.onOptionsItemSelected(item);
    }

    //
    // ========================================================

    @Override
    public void onLoginButton() {
        initLoginFragment();
    }

    @Override
    public void onRegistrationButton() {
        initRegistrationFragment();
    }

    @Override
    public void onRegistrationSuccess() {
        Intent openActivity = new Intent(this, DeckListActivity.class);
        startActivity(openActivity);
    }

    @Override
    public void onLoginSuccess() {
        Intent openActivity = new Intent(this, DeckListActivity.class);
        startActivity(openActivity);
    }
}
