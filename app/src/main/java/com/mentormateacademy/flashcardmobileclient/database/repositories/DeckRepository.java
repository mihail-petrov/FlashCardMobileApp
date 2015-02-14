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
        getDatabase().insert(DatabaseConfiguration.TABLE_DECKS, null, values);
    }

    @Override
    public ArrayList<Deck> readAll() {
        return null;
    }

    @Override
    public Cursor readAllCursor() {
        return null;
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
