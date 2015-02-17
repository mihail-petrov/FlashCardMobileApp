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


public class CardRepository extends Repository<Card> {

    public CardRepository(Context context) {
        super(context, DatabaseConfiguration.TABLE_CARDS);
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
    public Cursor readAllCursor() {
        return getDatabase().query(DatabaseConfiguration.TABLE_CARDS, null, null, null, null, null, null);
    }

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


//    public Cursor readByCursor(Bundle arguments) {
//
//        Set<String> tableColumns  = arguments.keySet();
//        Iterator<String> iterator = tableColumns.iterator();
//
//        ArrayList<String> where = new ArrayList<>();
//        StringBuilder builder   = new StringBuilder();
//
//        while(iterator.hasNext()) {
//            String element = iterator.next();
//
//            if(iterator.hasNext()) {
//                builder.append(element).append("=? AND ");
//            }
//            else {
//                builder.append(element).append("=?");
//            }
//
//            where.add(arguments.getString(element));
//            Log.d("DATA_DATA_DATA", arguments.getString(element));
//        }
//
//        // database query
//        String databaseQuery = builder.toString();
//
//        // where arguments array
//        String[] whereArguments = where.toArray(new String[where.size()]);
//
//        return getDatabase().query(DatabaseConfiguration.TABLE_CARDS, null,
//                databaseQuery, whereArguments, null, null, null);
//    }

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

    // CardRepository Utility Methods
    // =====================================

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
