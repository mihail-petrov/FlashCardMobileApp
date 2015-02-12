package com.mentormateacademy.flashcardmobileclient.customComponents;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;

public class RichEditText extends EditText {

    private CustomCallback optionMenuAction;

    public RichEditText(Context context) {
        super(context);

        optionMenuAction = new CustomCallback(RichEditText.this);
        init();
    }

    public RichEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        optionMenuAction = new CustomCallback(RichEditText.this);
        init();
    }

    public RichEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        optionMenuAction = new CustomCallback(RichEditText.this);
        init();
    }

    public void init() {

        if (Build.VERSION.SDK_INT >= 11) {
            this.setCustomSelectionActionModeCallback(optionMenuAction);
        }
    }
}
