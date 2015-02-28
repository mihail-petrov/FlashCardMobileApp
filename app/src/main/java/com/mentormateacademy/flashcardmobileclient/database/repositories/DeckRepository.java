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

    private long deckId;

    public DeckRepository(Context context) {
        super(context, DatabaseConfiguration.TABLE_DECKS);
    }

    @Override
    public void create(Deck element) {

        ContentValues values = getContentValueObject(element);
        this.deckId = getDatabase().insert(DatabaseConfiguration.TABLE_DECKS, null, values);
    }

    public long getLastInsertedId(){
        return this.deckId;
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
    public ArrayList<Deck> readBy(Bundle arguments) {

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

    @Override
    public void update(Deck element) {

    }

    //@Override
    public void update(Deck element, Bundle updateElement) {

        ContentValues values = getContentValueObject(element);
        buildWhereQuery(updateElement);

        getDatabase().update(DatabaseConfiguration.TABLE_DECKS, values, getWhereQuery(), getWhereArguments());
    }

    @Override
    public void delete(Deck element) {

    }

    // DeckRepository Utility Methods
    // =====================================

    public ContentValues getContentValueObject(Deck element){

        ContentValues values = new ContentValues(6);

        values.put(DatabaseConfiguration.TABLE_DECKS_TITLE, element.getTitle());
        values.put(DatabaseConfiguration.TABLE_DECKS_USER_ID, element.getUserId());
        values.put(DatabaseConfiguration.TABLE_DECKS_STRATEGY, element.getStrategyId());
        values.put(DatabaseConfiguration.TABLE_DECKS_CORRECT_STATS, element.getCorrectAnswers());
        values.put(DatabaseConfiguration.TABLE_DECKS_WRONG_STATS, element.getWrongAnswers());
        values.put(DatabaseConfiguration.TABLE_DECKS_CARD_COUNT, element.getCardSize());

        return values;
    }

    public Deck getDeck(Cursor cursorPointer) {

        // get all elements
        long id         = cursorPointer
                .getLong(cursorPointer.getColumnIndex(DatabaseConfiguration.TABLE_DECKS_ID));

        int strategyId  = cursorPointer
                .getInt(cursorPointer.getColumnIndex(DatabaseConfiguration.TABLE_DECKS_STRATEGY));

        int userId  = cursorPointer
                .getInt(cursorPointer.getColumnIndex(DatabaseConfiguration.TABLE_DECKS_USER_ID));


        String title    = cursorPointer
                .getString(cursorPointer.getColumnIndex(DatabaseConfiguration.TABLE_DECKS_TITLE));

        int correct     = cursorPointer
                .getInt(cursorPointer.getColumnIndex(DatabaseConfiguration.TABLE_DECKS_CORRECT_STATS));

        int wrong       = cursorPointer
                .getInt(cursorPointer.getColumnIndex(DatabaseConfiguration.TABLE_DECKS_WRONG_STATS));

        int cardCount   = cursorPointer
                .getInt(cursorPointer.getColumnIndex(DatabaseConfiguration.TABLE_DECKS_CARD_COUNT));

        // populate object
        Deck deck = new Deck();
        deck.setId(id);
        deck.setUserId(userId);
        deck.setTitle(title);
        deck.setStrategyId(strategyId);
        deck.setCorrectAnswers(correct);
        deck.setWrongAnswers(wrong);
        deck.setCardSize(cardCount);

        return deck;
    }
}
