package com.mentormateacademy.flashcardmobileclient.database.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import com.mentormateacademy.flashcardmobileclient.configurations.DatabaseConfiguration;
import com.mentormateacademy.flashcardmobileclient.database.interfaces.Repository;
import com.mentormateacademy.flashcardmobileclient.models.Deck;

import java.util.ArrayList;

public class DeckRepository extends Repository<Deck> {

    public DeckRepository(Context context) {
        super(context, DatabaseConfiguration.TABLE_DECKS);
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

            Deck deck = getDeck(deckCursorPointer);
            decksArrayList.add(deck);
        }

        return decksArrayList;
    }

    @Override
    public Cursor readAllCursor() {
        return getDatabase().query(DatabaseConfiguration.TABLE_DECKS, null, null, null, null, null, null);
    }


    public ArrayList<Deck> readBy(Bundle arguments){

        // create empty ArrayList
        ArrayList<Deck> decksArrayList = new ArrayList<>();

        // get cursor
        Cursor deckCursorPointer = readByCursor(arguments);

        // collect data
        while (deckCursorPointer.moveToNext()) {

            Deck deck = getDeck(deckCursorPointer);
            decksArrayList.add(deck);
        }

        return decksArrayList;
    }


//    public Cursor readByCursor(Bundle arguments){
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
//        }
//
//        // database query
//        String databaseQuery = builder.toString();
//
//        // where arguments array
//        String[] whereArguments = where.toArray(new String[where.size()]);
//
//        return getDatabase().query(DatabaseConfiguration.TABLE_DECKS, null,
//                databaseQuery, whereArguments, null, null, null);
//    }

    @Override
    public void update(Deck element) {

    }

    @Override
    public void delete(Deck element) {

    }

    @Override
    public void deleteAll() {
        getDatabase().delete(DatabaseConfiguration.TABLE_DECKS, null, null);
    }


    // DeckRepository Utility Methods
    // =====================================

    public Deck getDeck(Cursor cursorPointer){

        // get all elements
        long id             = cursorPointer.getLong(cursorPointer.getColumnIndex("_id"));
        long strategyId     = cursorPointer.getLong(cursorPointer.getColumnIndex("strategy_id"));
        String title        = cursorPointer.getString(cursorPointer.getColumnIndex("title"));

        Deck deck = new Deck();
        deck.setId(id);
        deck.setTitle(title);
        deck.setStrategyId(strategyId);

        return deck;
    }
}
