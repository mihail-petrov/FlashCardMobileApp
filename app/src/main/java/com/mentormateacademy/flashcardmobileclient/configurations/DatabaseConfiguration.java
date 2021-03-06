package com.mentormateacademy.flashcardmobileclient.configurations;

public interface DatabaseConfiguration {

    // @Database: flashcard_db
    // ==================================================
    public static final String DB_NAME = "flashcard_db";
    public static final int DB_VERSION = 1;

    // @Table: users
    // ==================================================
    public static final String TABLE_USERS          = "users";
    //
    public static final String TABLE_USERS_ID       = "_id";
    public static final String TABLE_USERS_EMAIL    = "email";
    public static final String TABLE_USERS_PASS     = "password";

    // @Query: create new user
    public static final String TABLE_USERS_CREATE_QUERY = "create table " +
            TABLE_USERS         + "(" +
            TABLE_USERS_ID      + " integer primary key autoincrement, " +
            TABLE_USERS_EMAIL   + " text not null, " +
            TABLE_USERS_PASS    + " text not null)";

    // @Query: delete user table
    public static final String TABLE_USERS_DROP_QUERY = "drop table " + TABLE_USERS;


    // @Table: decks
    // ==================================================
    public static final String TABLE_DECKS                  = "decks";
    //
    public static final String TABLE_DECKS_ID               = "_id";

    public static final String TABLE_DECKS_USER_ID          = "user_id";

    public static final String TABLE_DECKS_TITLE            = "title";
    public static final String TABLE_DECKS_STRATEGY         = "strategy_id";
    public static final String TABLE_DECKS_CORRECT_STATS    = "correct";
    public static final String TABLE_DECKS_WRONG_STATS      = "wrong";
    public static final String TABLE_DECKS_CARD_COUNT       = "card_count";

    // @Query: create new deck
    public static final String TABLE_DECKS_CREATE_QUERY = "create table " +
            TABLE_DECKS + "(" +
            TABLE_DECKS_ID              + " integer primary key autoincrement, " +
            TABLE_DECKS_USER_ID         + " integer not null, " +
            TABLE_DECKS_STRATEGY        + " integer not null, " +
            TABLE_DECKS_CORRECT_STATS   + " integer not null default 0, " +
            TABLE_DECKS_WRONG_STATS     + " integer not null default 0, " +
            TABLE_DECKS_CARD_COUNT      + " integer not null default 0, " +
            TABLE_DECKS_TITLE           + " text not null)";

    // @Query: delete deck table
    public static final String TABLE_DECKS_DROP_QUERY = "drop table " + TABLE_DECKS;


    // @Table: cards
    // ==================================================
    public static final String TABLE_CARDS                  = "cards";
    //
    public static final String TABLE_CARDS_ID               = "_id";
    public static final String TABLE_CARDS_DECK_ID          = "deck_id";
    public static final String TABLE_CARDS_FRONT_TITLE      = "front_title";
    public static final String TABLE_CARDS_FRONT_CONTENT    = "front_content";
    public static final String TABLE_CARDS_BACK_TITLE       = "back_title";
    public static final String TABLE_CARDS_BACK_CONTENT     = "back_content";
    public static final String TABLE_CARDS_EXTRA_TITLE      = "extra_title";
    public static final String TABLE_CARDS_EXTRA_CONTENT    = "extra_content";

    // @Query: create new card
    public static final String TABLE_CARDS_CREATE_QUERY = "create table " +
            TABLE_CARDS + "(" +

            // Key columns
            TABLE_CARDS_ID + " integer primary key autoincrement, " +
            TABLE_CARDS_DECK_ID + " integer not null, " +

            // Content columns
            TABLE_CARDS_FRONT_TITLE + " text not null, " +
            TABLE_CARDS_FRONT_CONTENT + " text not null, " +

            TABLE_CARDS_BACK_TITLE + " text not null, " +
            TABLE_CARDS_BACK_CONTENT + " text not null, " +

            TABLE_CARDS_EXTRA_TITLE + " text null, " +
            TABLE_CARDS_EXTRA_CONTENT + " integer null);";

    // @Query: delete cards table
    public static final String TABLE_CARDS_DROP_QUERY = "drop table " + TABLE_CARDS;
}
