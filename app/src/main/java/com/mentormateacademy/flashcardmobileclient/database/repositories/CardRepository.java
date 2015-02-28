package com.mentormateacademy.flashcardmobileclient.database.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.mentormateacademy.flashcardmobileclient.configurations.DatabaseConfiguration;
import com.mentormateacademy.flashcardmobileclient.database.interfaces.Repository;
import com.mentormateacademy.flashcardmobileclient.models.Card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;


public class CardRepository extends Repository<Card> {

    public CardRepository(Context context) {
        super(context, DatabaseConfiguration.TABLE_CARDS);
    }

    public void create(Bundle arguments) {

        ContentValues values = ConvertBundleToContentValue(arguments);
        getDatabase().insert(DatabaseConfiguration.TABLE_CARDS, null, values);

        Log.d("DB_HELPER", "Insert record in table " + DatabaseConfiguration.TABLE_CARDS);
    }


    private ContentValues ConvertBundleToContentValue(Bundle arguments){

        // check bundle length
        int length = arguments.size();

        // create content values object
        ContentValues values = new ContentValues(length);

        // get Bundle keys
        Set<String> bundleKeys = arguments.keySet();

        // get Bundle iterator
        Iterator<String> iterator = bundleKeys.iterator();

        while (iterator.hasNext()) {

            String keyElement = iterator.next();
            values.put(keyElement, arguments.getString(keyElement));
        }

        return values;
    }

    @Override
    public void create(Card element) {

        ContentValues values = getContentValueObject(element);
        getDatabase().insert(DatabaseConfiguration.TABLE_CARDS, null, values);

        Log.d("DB_HELPER", "Insert record in table " + DatabaseConfiguration.TABLE_CARDS);
    }

    @Override
    public ArrayList<Card> readAll() {

        // create empty ArrayList
        ArrayList<Card> cardArrayList = new ArrayList<>();

        // get cursor
        Cursor cardCursorPointer = readAllCursor();
        while (cardCursorPointer.moveToNext()) {

            Card cardObject = getCard(cardCursorPointer);
            cardArrayList.add(cardObject);
        }

        return cardArrayList;
    }

    @Override
    public ArrayList<Card> readBy(Bundle arguments){

        // create empty ArrayList
        ArrayList<Card> cardArrayList = new ArrayList<>();

        Cursor cardCursorPointer = readByCursor(arguments);
        while (cardCursorPointer.moveToNext()) {

            Card cardObject = getCard(cardCursorPointer);
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

    public void update(Card element, Bundle updateElement) {

        ContentValues values = getContentValueObject(element);
        buildWhereQuery(updateElement);

        getDatabase().update(DatabaseConfiguration.TABLE_CARDS, values, getWhereQuery(), getWhereArguments());
    }



    public void delete(Bundle deleteElement) {
        buildWhereQuery(deleteElement);
        getDatabase().delete(DatabaseConfiguration.TABLE_CARDS, getWhereQuery(), getWhereArguments());
    }


    // CardRepository Utility Methods
    // =====================================

    public ContentValues getContentValueObject(Card element){

        ContentValues values = new ContentValues(7);
        values.put("deck_id", element.getDeckId());

        values.put("front_title", element.getFrontTitle());
        values.put("front_content", element.getFrontContent());

        values.put("back_title", element.getBackTitle());
        values.put("back_content", element.getBackContent());

        values.put("extra_title", element.getExtraTitle());
        values.put("extra_content", element.getExtraContent());

        return values;
    }

    public Card getCard(Cursor cursorPointer){

        // get all database fields
        long id     = cursorPointer.getLong(cursorPointer.getColumnIndex("_id"));
        long deckId = cursorPointer.getLong(cursorPointer.getColumnIndex("deck_id"));

        String frontTitle   = cursorPointer.getString(cursorPointer.getColumnIndex("front_title"));
        String frontContent = cursorPointer.getString(cursorPointer.getColumnIndex("front_content"));

        String backTitle   = cursorPointer.getString(cursorPointer.getColumnIndex("back_title"));
        String backContent = cursorPointer.getString(cursorPointer.getColumnIndex("back_content"));

        String extraTitle   = cursorPointer.getString(cursorPointer.getColumnIndex("extra_title"));
        String extraContent = cursorPointer.getString(cursorPointer.getColumnIndex("extra_content"));


        Card cardObject = new Card();
        cardObject.setId(id);
        cardObject.setDeckId(deckId);
        cardObject.setFrontTitle(frontTitle);
        cardObject.setFrontContent(frontContent);
        cardObject.setBackTitle(backTitle);
        cardObject.setBackContent(backContent);
        cardObject.setExtraTitle(extraTitle);
        cardObject.setExtraContent(extraContent);

        return cardObject;
    }
}
