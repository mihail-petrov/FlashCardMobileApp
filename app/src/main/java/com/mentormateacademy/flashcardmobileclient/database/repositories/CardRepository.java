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

        // create empty ArrayList
        ArrayList<Card> cardArrayList = new ArrayList<>();

        // get cursor
        Cursor cardCursorPointer = readAllCursor();
        while (cardCursorPointer.moveToNext()) {

            // get all database fields
            long id = cardCursorPointer.getLong(cardCursorPointer
                    .getColumnIndex("_id"));
            long deckId = cardCursorPointer.getLong(cardCursorPointer
                    .getColumnIndex("deck_id"));

            String frontTitle = cardCursorPointer.getString(cardCursorPointer
                    .getColumnIndex("front_title"));
            String frontContent = cardCursorPointer.getString(cardCursorPointer
                    .getColumnIndex("front_content"));

            String backTitle = cardCursorPointer.getString(cardCursorPointer
                    .getColumnIndex("back_title"));
            String backContent = cardCursorPointer.getString(cardCursorPointer
                    .getColumnIndex("back_content"));

            String extraTitle = cardCursorPointer.getString(cardCursorPointer
                    .getColumnIndex("extra_title"));
            String extraContent = cardCursorPointer.getString(cardCursorPointer
                    .getColumnIndex("extra_content"));


            Card cardObject = new Card();
            cardObject.setId(id);
            cardObject.setDeckId(deckId);
            cardObject.setFrontTitle(frontTitle);
            cardObject.setFrontContent(frontContent);
            cardObject.setBackTitle(backTitle);
            cardObject.setBackContent(backContent);
            cardObject.setExtraTitle(extraTitle);
            cardObject.setExtraContent(extraContent);

            cardArrayList.add(cardObject);
        }

        return cardArrayList;
    }

    @Override
    public Cursor readAllCursor() {
        return getDatabase().query(DatabaseConfiguration.TABLE_CARDS, null, null, null, null, null, null);
    }

    public Cursor readAllBaseOnDeckId(long deck_id) {
        return getDatabase().query(DatabaseConfiguration.TABLE_CARDS, null, "deck_id=" + deck_id, null, null, null, null);
    }

    public ArrayList<Card>  readAllBaseOnDeckIdObject(long deck_id) {

        // create empty ArrayList
        ArrayList<Card> cardArrayList = new ArrayList<>();

        // get cursor
        Cursor cardCursorPointer = readAllBaseOnDeckId(deck_id);

        while (cardCursorPointer.moveToNext()) {

            // get all database fields
            long id = cardCursorPointer.getLong(cardCursorPointer
                    .getColumnIndex("_id"));
            long deckId = cardCursorPointer.getLong(cardCursorPointer
                    .getColumnIndex("deck_id"));

            String frontTitle = cardCursorPointer.getString(cardCursorPointer
                    .getColumnIndex("front_title"));
            String frontContent = cardCursorPointer.getString(cardCursorPointer
                    .getColumnIndex("front_content"));

            String backTitle = cardCursorPointer.getString(cardCursorPointer
                    .getColumnIndex("back_title"));
            String backContent = cardCursorPointer.getString(cardCursorPointer
                    .getColumnIndex("back_content"));

            String extraTitle = cardCursorPointer.getString(cardCursorPointer
                    .getColumnIndex("extra_title"));
            String extraContent = cardCursorPointer.getString(cardCursorPointer
                    .getColumnIndex("extra_content"));


            Card cardObject = new Card();
            cardObject.setId(id);
            cardObject.setDeckId(deckId);
            cardObject.setFrontTitle(frontTitle);
            cardObject.setFrontContent(frontContent);
            cardObject.setBackTitle(backTitle);
            cardObject.setBackContent(backContent);
            cardObject.setExtraTitle(extraTitle);
            cardObject.setExtraContent(extraContent);

            cardArrayList.add(cardObject);
        }

        return cardArrayList;

    }


    @Override
    public void update(Card element) {

    }

    @Override
    public void delete(Card element) {

    }

    @Override
    public void deleteAll() {
        getDatabase().delete(DatabaseConfiguration.TABLE_CARDS, null, null);
    }
}
