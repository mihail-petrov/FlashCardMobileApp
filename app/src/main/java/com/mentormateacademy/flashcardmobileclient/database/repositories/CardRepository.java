package com.mentormateacademy.flashcardmobileclient.database.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.mentormateacademy.flashcardmobileclient.configurations.DatabaseConfiguration;
import com.mentormateacademy.flashcardmobileclient.database.interfaces.Repository;
import com.mentormateacademy.flashcardmobileclient.models.Card;

import java.util.ArrayList;


public class CardRepository extends Repository<Card> {

    public CardRepository(Context context) {
        super(context);
    }

    @Override
    public void create(Card element) {
        ContentValues values = new ContentValues(7);
        values.put("deck_id", element.getDeckId());

        values.put("front_title", element.getFrontTitle());
        values.put("front_content", element.getFrontContent());

        values.put("back_title", element.getBackTitle());
        values.put("back_content", element.getBackContent());

        values.put("extra_title", element.getExtraTitle());
        values.put("extra_content", element.getExtraContent());

        getDatabase().insert(DatabaseConfiguration.TABLE_CARDS, null, values);

        Log.d("DB_HELPER", "Insert record in table " + DatabaseConfiguration.TABLE_DECKS);
    }

    @Override
    public ArrayList<Card> readAll() {
        return null;
    }

    @Override
    public Cursor readAllCursor() {
        return null;
    }

    @Override
    public void update(Card element) {

    }

    @Override
    public void delete(Card element) {

    }

    @Override
    public void deleteAll() {

    }
}
