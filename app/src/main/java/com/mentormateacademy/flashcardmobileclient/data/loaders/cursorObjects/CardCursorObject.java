package com.mentormateacademy.flashcardmobileclient.data.loaders.cursorObjects;

import android.content.Context;

import com.mentormateacademy.flashcardmobileclient.data.adapters.cursor.CardCursorAdapter;
import com.mentormateacademy.flashcardmobileclient.data.loaders.cursorProviders.CardCursorProvider;
import com.mentormateacademy.flashcardmobileclient.data.loaders.interfaces.BaseCursorLoader;


public class CardCursorObject {

    private BaseCursorLoader cursorLoader;

    public CardCursorObject(Context context, long id) {

        // cursor adapter
        CardCursorAdapter cursorAdapter = new CardCursorAdapter(context, null);

        // cursor provider
        CardCursorProvider cursorProvider = new CardCursorProvider(context, id);

        // cursor loader
        cursorLoader = new BaseCursorLoader(cursorAdapter, cursorProvider);
    }

    public BaseCursorLoader getCursorLoader(){
        return cursorLoader;
    }
}
