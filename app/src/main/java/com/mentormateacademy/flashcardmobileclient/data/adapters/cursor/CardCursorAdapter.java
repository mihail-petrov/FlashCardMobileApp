package com.mentormateacademy.flashcardmobileclient.data.adapters.cursor;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.configurations.DatabaseConfiguration;

public class CardCursorAdapter extends CursorAdapter {

    public CardCursorAdapter(Context context, Cursor c) {
        super(context, c, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View cursorView = LayoutInflater.from(context).inflate(R.layout.card_list_item, parent, false);
        ViewHolder holder = new ViewHolder();

        holder.title = (TextView) cursorView.findViewById(R.id.cadContentTextView);
        cursorView.setTag(holder);

        return cursorView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder holder = (ViewHolder) view.getTag();

        // CARD CONTENT
        String cardContent = cursor.getString(cursor.getColumnIndex(DatabaseConfiguration.TABLE_CARDS_FRONT_CONTENT));
        String titleResult = Html.fromHtml((String) cardContent).toString();
        holder.title.setText(titleResult);
    }

    private class ViewHolder {
        public TextView title;
    }

}
