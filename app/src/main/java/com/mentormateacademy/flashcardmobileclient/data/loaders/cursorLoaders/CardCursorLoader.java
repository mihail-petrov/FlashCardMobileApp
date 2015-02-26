package com.mentormateacademy.flashcardmobileclient.data.loaders.cursorLoaders;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;

import com.mentormateacademy.flashcardmobileclient.data.adapters.cursor.CardCursorAdapter;
import com.mentormateacademy.flashcardmobileclient.data.loaders.cursorProviders.CardCursorProvider;

public class CardCursorLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    private CursorAdapter cursorAdapter;
    private CardCursorProvider cursorProvider;

    public CardCursorLoader(Context context, long deckId) {

        // load cursor adapter
        this.cursorAdapter = new CardCursorAdapter(context, null);

        // load cursor provider
        this.cursorProvider = new CardCursorProvider(context, deckId);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return this.cursorProvider;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        this.cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        this.cursorAdapter.swapCursor(null);
    }

    public CursorAdapter getCursorAdapter() {
        return this.cursorAdapter;
    }
}
