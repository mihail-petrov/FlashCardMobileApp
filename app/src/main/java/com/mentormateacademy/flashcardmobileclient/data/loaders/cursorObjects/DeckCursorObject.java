package com.mentormateacademy.flashcardmobileclient.data.loaders.cursorObjects;

import android.content.Context;

import com.mentormateacademy.flashcardmobileclient.data.adapters.cursor.DeckCursorAdapter;
import com.mentormateacademy.flashcardmobileclient.data.loaders.cursorProviders.DeckCursorProvider;
import com.mentormateacademy.flashcardmobileclient.data.loaders.interfaces.BaseCursorLoader;

public class DeckCursorObject {

    private BaseCursorLoader cursorLoader;

    public DeckCursorObject(Context context, long id) {

        // cursor adapter
        DeckCursorAdapter adapter = new DeckCursorAdapter(context, null);

        // cursor provider
        DeckCursorProvider cursorProvider = new DeckCursorProvider(context, id);

        // get cursor loader
        cursorLoader = new BaseCursorLoader(adapter, cursorProvider);
    }

    public BaseCursorLoader getCursorLoader(){
        return cursorLoader;
    }
}
