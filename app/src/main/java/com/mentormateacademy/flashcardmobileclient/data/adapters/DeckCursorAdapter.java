package com.mentormateacademy.flashcardmobileclient.data.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mentormateacademy.flashcardmobileclient.R;


public class DeckCursorAdapter extends CursorAdapter {

    public DeckCursorAdapter(Context context, Cursor c) {
        super(context, c, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        View cursorView = LayoutInflater.from(context).inflate(R.layout.deck_list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder();

        holder.title = (TextView) cursorView.findViewById(R.id.deckTitleTextView);
        cursorView.setTag(holder);

        return cursorView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.title.setText(cursor.getString(cursor.getColumnIndex("title")));
    }

    private class ViewHolder {
        public TextView title;
    }
}
