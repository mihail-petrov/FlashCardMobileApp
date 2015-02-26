package com.mentormateacademy.flashcardmobileclient.data.loaders.interfaces;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.AsyncTaskLoader;

public abstract class BaseCursorProvider extends AsyncTaskLoader<Cursor> {

    private Cursor mCursor;

    public BaseCursorProvider(Context context) {
        super(context);
    }

    @Override
    public abstract Cursor loadInBackground();

    @Override
    public void deliverResult(Cursor cursor) {

        // check if cursor is reset
        boolean isReset = isReset();

        if (isReset) {
            if (cursor != null) {
                cursor.close();
            }
            return;
        }

        Cursor oldCursor = mCursor;
        mCursor = cursor;

        // check if cursor is started
        if (isStarted()) {
            super.deliverResult(cursor);
        }

        // start new cursor
        if (oldCursor != null && oldCursor != cursor && !oldCursor.isClosed()) {
            oldCursor.close();
        }
    }

    @Override
    protected void onStartLoading() {
        if (mCursor != null) {
            deliverResult(mCursor);
        }
        if (takeContentChanged() || mCursor == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    public void onCanceled(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();

        if (mCursor != null && !mCursor.isClosed()) {
            mCursor.close();
        }
        mCursor = null;
    }
}
