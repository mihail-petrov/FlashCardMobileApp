package com.mentormateacademy.flashcardmobileclient.component.customComponents.extendRadioGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import java.util.ArrayList;

public class ExtendRadioGroup extends LinearLayout {

    private ArrayList<RadioButton> radioButtonColection;
    private View checkedView;

    // Constructors - CustomRadioGroup
    // ========================================
    public ExtendRadioGroup(Context context) {
        super(context);
    }

    public ExtendRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        radioButtonColection = traverseGroupChildren();
        super.onFinishInflate();
    }


    // ExtendRadioGroup public methods
    // ========================================

    public void selectRadioButton(int radioButtonId){
        for (RadioButton element : radioButtonColection) {
            boolean isChecked = (element.getId() == radioButtonId);
            element.setChecked(isChecked);
        }
    }

    // Custom Callback Event Object
    // ========================================
    private OnClickListener clickOnRadioButton = new OnClickListener() {

        @Override
        public void onClick(View v) {

            for (RadioButton element : radioButtonColection) {
                element.setChecked(false);
            }

            if (v instanceof RadioButton) {
                RadioButton button = (RadioButton) v;
                button.setChecked(true);

                checkedView = button;
                onRadioButtonSelected();
            }
        }
    };


    // get all radio buttons
    private ArrayList<RadioButton> traverseGroupChildren() {

        ArrayList<RadioButton> buttons = new ArrayList<>();
        ViewGroup viewGroup = (ViewGroup) this.getRootView();

        parseRadioButton(viewGroup, buttons);

        return buttons;
    }

    // get single radio button element
    private void parseRadioButton(ViewGroup viewGroup, ArrayList<RadioButton> radioButtons) {

        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);

            if (view instanceof ViewGroup) {
                parseRadioButton((ViewGroup) view, radioButtons);
            } else if (view instanceof RadioButton) {

                RadioButton button = (RadioButton) view;
                button.setOnClickListener(clickOnRadioButton);
                radioButtons.add(button);
            }
        }
    }

    // Callback Interface - Event Handler
    // ========================================
    private ClickOnRadioButton clickOnRadioButtonCallback;

    public interface ClickOnRadioButton {
        public void selectRadioButton(View checkedView);
    }

    public void setOnRadioButtonSelection(ClickOnRadioButton callback) {
        this.clickOnRadioButtonCallback = callback;
    }

    private void onRadioButtonSelected() {
        if (this.clickOnRadioButtonCallback != null) {
            this.clickOnRadioButtonCallback.selectRadioButton(this.checkedView);
        }
    }
}


