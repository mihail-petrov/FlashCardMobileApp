package com.mentormateacademy.flashcardmobileclient.data.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mentormateacademy.flashcardmobileclient.R;

public class CardCursorAdapter extends CursorAdapter {

    public CardCursorAdapter(Context context, Cursor c) {
        super(context, c, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View cursorView = LayoutInflater.from(context).inflate(R.layout.card_list_item, parent, false);
        ViewHolder holder = new ViewHolder();

        holder.title = (TextView) cursorView.findViewById(R.id.cardTitleTextView);
        cursorView.setTag(holder);

        return cursorView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.title.setText(cursor.getString(cursor.getColumnIndex("front_title")));
    }

    private class ViewHolder {
        public TextView title;
    }

}
