package com.mentormateacademy.flashcardmobileclient.database.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.mentormateacademy.flashcardmobileclient.configurations.DatabaseConfiguration;
import com.mentormateacademy.flashcardmobileclient.database.interfaces.Repository;
import com.mentormateacademy.flashcardmobileclient.models.Deck;

import java.util.ArrayList;

public class DeckRepository extends Repository<Deck> {

    public DeckRepository(Context context) {
        super(context);
    }

    @Override
    public void create(Deck element) {
        ContentValues values = new ContentValues(1);

        values.put("title", element.getTitle());
        values.put("strategy_id", element.getStrategyId());
        getDatabase().insert(DatabaseConfiguration.TABLE_DECKS, null, values);
    }

    @Override
    public ArrayList<Deck> readAll() {
        // create empty ArrayList
        ArrayList<Deck> decksArrayList = new ArrayList<>();

        // get cursor
        Cursor deckCursorPointer = readAllCursor();
        while (deckCursorPointer.moveToNext()) {

            // get all elements
            long id = deckCursorPointer.getLong(deckCursorPointer.getColumnIndex("_id"));
            String title = deckCursorPointer.getString(deckCursorPointer.getColumnIndex("title"));

            Deck deck = new Deck();
            deck.setId(id);
            deck.setTitle(title);

            decksArrayList.add(deck);
        }

        return decksArrayList;
    }

    @Override
    public Cursor readAllCursor() {
        return getDatabase().query(DatabaseConfiguration.TABLE_DECKS, null, null, null, null, null, null);
    }

    @Override
    public void update(Deck element) {

    }

    @Override
    public void delete(Deck element) {

    }

    @Override
    public void deleteAll() {

    }
}
