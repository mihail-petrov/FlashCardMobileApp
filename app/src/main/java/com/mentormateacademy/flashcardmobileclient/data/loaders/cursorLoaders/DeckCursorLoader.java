package com.mentormateacademy.flashcardmobileclient.data.loaders.cursorLoaders;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mentormateacademy.flashcardmobileclient.data.adapters.cursor.DeckCursorAdapter;
import com.mentormateacademy.flashcardmobileclient.data.loaders.cursorProviders.DeckCursorProvider;

public class DeckCursorLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    private DeckCursorProvider cursorProvider;

    private DeckCursorAdapter deckCursorAdapter;

    public DeckCursorLoader(Context context, long userId) {

        // load cursor Adapter
        this.deckCursorAdapter = new DeckCursorAdapter(context, null);

        // load cursor Provider
        this.cursorProvider = new DeckCursorProvider(context, userId);
    }

    public DeckCursorAdapter getCursorAdapter() {
        return this.deckCursorAdapter;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle bundle) {
        return this.cursorProvider;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        this.deckCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        this.deckCursorAdapter.swapCursor(null);
    }
}
