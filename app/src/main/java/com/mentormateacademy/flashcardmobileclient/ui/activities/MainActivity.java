package com.mentormateacademy.flashcardmobileclient.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.mentormateacademy.flashcardmobileclient.R;

public class MainActivity extends ActionBarActivity {

    private LinearLayout linearLayoutMain;

    public static final double BASE_INCHES = 3.88730126323;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setObserverOnMainLayout();

    }

    private void setObserverOnMainLayout() {
        linearLayoutMain = (LinearLayout) findViewById(R.id.mainLinearLayout);
        final ViewTreeObserver treeObserver = linearLayoutMain.getViewTreeObserver();

        final ViewTreeObserver.OnGlobalLayoutListener layoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int currentApiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentApiVersion >= 16) {
                    linearLayoutMain.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    linearLayoutMain.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }

                //Check if a there is any fragment on the screen
                try {
                    LogInFragment logInFragment = (LogInFragment) getSupportFragmentManager().findFragmentById(R.id.container);
                    SignUpFragment signUpFragment = (SignUpFragment) getSupportFragmentManager().findFragmentById(R.id.container);
                    if(logInFragment == null && signUpFragment == null){
                        addInitialFragment();
                    }
                }catch (ClassCastException e){
                    e.printStackTrace();
                }
            }
        };
        treeObserver.addOnGlobalLayoutListener(layoutListener);
    }

    private void addInitialFragment() {
        LogInFragment logInFragment = new LogInFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.container, logInFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
