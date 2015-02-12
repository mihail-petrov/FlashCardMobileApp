package com.mentormateacademy.flashcardmobileclient.configurations;

public class DatabaseConfiguration {

    // @Table: decks
    // ==================================================
    public static final String TABLE_DECKS = "decks";
    // @Query: delete deck table
    public static final String TABLE_DECKS_DROP_QUERY = "drop table " + TABLE_DECKS;
    public static final String TABLE_DECKS_ID = "_id";
    public static final String TABLE_DECKS_TITLE = "title";
    // @Query: create new deck
    public static final String TABLE_DECKS_CREATE_QUERY = "create table " +
            TABLE_DECKS + "(" +
            TABLE_DECKS_ID + " integer primary key autoincrement, " +
            TABLE_DECKS_TITLE + " text not null)";
    // @Table: cards
    // ==================================================
    public static final String TABLE_CARDS = "cards";
    // @Query: delete cards table
    public static final String TABLE_CARDS_DROP_QUERY = "drop table " + TABLE_CARDS;
    public static final String TABLE_CARDS_ID = "_id";
    public static final String TABLE_CARDS_FRONT = "front";
    public static final String TABLE_CARDS_BACK = "back";
    public static final String TABLE_CARDS_EXTRA = "extra";
    // @Query: create new card
    public static final String TABLE_CARDS_CREATE_QUERY = "create table " +
            TABLE_CARDS + "(" +
            TABLE_CARDS_ID + " integer primary key autoincrement, " +
            TABLE_CARDS_FRONT + " text not null, " +
            TABLE_CARDS_BACK + " text not null, " +
            TABLE_CARDS_EXTRA + " integer not null);";


    private DatabaseConfiguration() {
    }
}
