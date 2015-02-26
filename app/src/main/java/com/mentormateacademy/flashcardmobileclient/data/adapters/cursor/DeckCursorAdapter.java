package com.mentormateacademy.flashcardmobileclient.data.adapters.cursor;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.configurations.DatabaseConfiguration;
import com.mentormateacademy.flashcardmobileclient.configurations.StrategyConfiguration;
import com.mentormateacademy.flashcardmobileclient.database.helper.DatabaseRepository;
import com.mentormateacademy.flashcardmobileclient.database.repositories.CardRepository;


public class DeckCursorAdapter extends CursorAdapter {

    private CardRepository cardRepository;

    public DeckCursorAdapter(Context context, Cursor c) {
        super(context, c, false);

        this.cardRepository = DatabaseRepository.getRepository(context).getCardRepository();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        View cursorView = LayoutInflater.from(context).inflate(R.layout.deck_list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder();

        holder.title    = (TextView)  cursorView.findViewById(R.id.deckTitleTextView);
        holder.deckIcon = (ImageView) cursorView.findViewById(R.id.deckIcon);
        holder.cardSize = (TextView)  cursorView.findViewById(R.id.cardSize);

        cursorView.setTag(holder);

        return cursorView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder holder = (ViewHolder) view.getTag();

        // DECK TITLE
        String deckTitle = cursor.getString(cursor.getColumnIndex(DatabaseConfiguration.TABLE_DECKS_TITLE));
        holder.title.setText(deckTitle);

        // CARD COUNT
        int cardCount = cursor.getInt(cursor.getColumnIndex(DatabaseConfiguration.TABLE_DECKS_CARD_COUNT));
        holder.cardSize.setText(String.valueOf(cardCount));


        // SET PROPER ICON
        int strategyId = cursor.getInt(cursor.getColumnIndex(DatabaseConfiguration.TABLE_DECKS_STRATEGY));

        if(strategyId == StrategyConfiguration.SPACED_REPETITION) {
            holder.deckIcon.setBackgroundDrawable(context.getResources()
                    .getDrawable(R.drawable.circle_background));
        }

        if(strategyId == StrategyConfiguration.SHUFFLE_ORDER) {
            holder.deckIcon.setBackgroundDrawable(context.getResources()
                    .getDrawable(R.drawable.circle_background_red));
        }

        if(strategyId == StrategyConfiguration.ORDER_PROGRESS) {
            holder.deckIcon.setBackgroundDrawable(context.getResources()
                    .getDrawable(R.drawable.circle_background_orange));
        }
    }

    private class ViewHolder {
        public TextView title;
        public ImageView deckIcon;
        public TextView cardSize;
    }
}
