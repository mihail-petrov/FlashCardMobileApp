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


    // Bottom Toolbar Managment
    // ==================================================

    public View getBottomToolbar(){
        return this.bottomToolbar;
    }

    public void setOnBottomToolbarItemSelected(View v){

    }

    // Fragment Managment
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

    public void setFragmentAnimation(int enter, int exit, int popEnter, int popExit) {

        animationBundle = new Bundle();

        animationBundle.putInt("ENTER_ANIMATION", enter);
        animationBundle.putInt("EXIT_ANIMATION", exit);
        animationBundle.putInt("POP_ENTER_ANIMATION", popEnter);
        animationBundle.putInt("POP_EXIT_ANIMATION", popExit);
    }

    public Bundle setFragmentAnimation(int left, int right) {

        animationBundle = new Bundle();


        animationBundle.putInt("ENTER_ANIMATION", left);
        animationBundle.putInt("EXIT_ANIMATION", right);

        return animationBundle;
    }

    public void setFragmentWithAnimation(Fragment element,Bundle animationBundle, boolean putIntoBackStack){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        int enter    = animationBundle.getInt("ENTER_ANIMATION", 0);
        int exit     = animationBundle.getInt("EXIT_ANIMATION", 0);

//        int popEnter;
//        if(animationBundle.getInt("POP_ENTER_ANIMATION", 0) != 0) {
//            popEnter = animationBundle.getInt("POP_ENTER_ANIMATION", 0);
//        }
//
//        int popExit;
//        if(animationBundle.getInt("POP_EXIT_ANIMATION", 0) != 0) {
//            popExit  = animationBundle.getInt("POP_EXIT_ANIMATION", 0);
//        }

        if(putIntoBackStack) {
            transaction.addToBackStack(null).setCustomAnimations(enter, exit)
                    .replace(R.id.fragmentContainer, element).commit();
        }
        else {
            transaction.setCustomAnimations(enter, exit)
                    .replace(R.id.fragmentContainer, element).commit();
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
