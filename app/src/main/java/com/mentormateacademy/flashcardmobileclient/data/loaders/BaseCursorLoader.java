package com.mentormateacademy.flashcardmobileclient.data.loaders;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;


public class BaseCursorLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    private CursorAdapter cursorAdapter;
    private BaseCursorProvider cursorProvider;

    public BaseCursorLoader(CursorAdapter cursorAdapter, BaseCursorProvider cursorProvider){
        this.cursorAdapter = cursorAdapter;
        this.cursorProvider = cursorProvider;
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

    public CursorAdapter getCursorAdapter(){
        return cursorAdapter;
    }
}
