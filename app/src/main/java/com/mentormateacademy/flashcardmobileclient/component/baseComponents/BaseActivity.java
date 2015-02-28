package com.mentormateacademy.flashcardmobileclient.component.baseComponents;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;

import com.mentormateacademy.flashcardmobileclient.R;

import java.util.ArrayList;

public class BaseActivity extends ActionBarActivity {

    protected Toolbar topToolbar;

    private Bundle animationBundle;

    private View bottomToolbar;

    private ArrayList<View> toolbarViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_view);

        topToolbar = (Toolbar) findViewById(R.id.topToolbar);
        setSupportActionBar(topToolbar);
    }

    public void setToolbarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    public void inflateBottomToolbar(int bottomToolbarId){

        // load bottom toolbar
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(bottomToolbarId);
        this.bottomToolbar = stub.inflate();
    }


    // Bottom Toolbar Manager
    // ==================================================

    public View getBottomToolbar(){
        return this.bottomToolbar;
    }

    // Fragment Manager
    // ==================================================

    protected void setFragment(Fragment element, boolean putIntoBackStack){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(putIntoBackStack) {
            transaction.addToBackStack(null)
                    .replace(R.id.fragmentContainer, element).commit();
        }
        else {
            transaction.replace(R.id.fragmentContainer, element).commit();
        }

        hideKeyboard();
    }

    private void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
