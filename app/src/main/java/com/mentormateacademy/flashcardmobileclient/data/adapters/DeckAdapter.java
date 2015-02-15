package com.mentormateacademy.flashcardmobileclient.data.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DeckAdapter extends CursorAdapter {

    public DeckAdapter(Context context, Cursor c) {
        super(context, c, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

//        View cursorView = LayoutInflater.from(context).inflate(R.layout.deck_layout_test, viewGroup, false);
//        ViewHolder holder = new ViewHolder();
//        holder.title = (TextView) cursorView.findViewById(R.id.deckTitle);

//        cursorView.setTag(holder);
//
//        return cursorView;

        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        Log.d("RESULT_VIEW", cursor.getString(cursor.getColumnIndex("title")));

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.title.setText(cursor.getString(cursor.getColumnIndex("title")));
    }

    private class ViewHolder {
        public TextView title;
    }
}