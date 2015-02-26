package com.mentormateacademy.flashcardmobileclient.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mentormateacademy.flashcardmobileclient.configurations.DatabaseConfiguration;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, DatabaseConfiguration.DB_NAME, null, DatabaseConfiguration.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // create table user
        db.execSQL(DatabaseConfiguration.TABLE_USERS_CREATE_QUERY);
        Log.d("DB_HELPER_LOG", "Create table query : " + DatabaseConfiguration.TABLE_USERS_CREATE_QUERY);

        // create table deck
        db.execSQL(DatabaseConfiguration.TABLE_DECKS_CREATE_QUERY);
        Log.d("DB_HELPER_LOG", "Create table query : " + DatabaseConfiguration.TABLE_DECKS_CREATE_QUERY);

        // create table cards
        db.execSQL(DatabaseConfiguration.TABLE_CARDS_CREATE_QUERY);
        Log.d("DB_HELPER_LOG", "Create table query : " + DatabaseConfiguration.TABLE_CARDS_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // drop user table
        db.execSQL(DatabaseConfiguration.TABLE_USERS_DROP_QUERY);

        // drop card table
        db.execSQL(DatabaseConfiguration.TABLE_DECKS_DROP_QUERY);

        // drop notes table
        db.execSQL(DatabaseConfiguration.TABLE_CARDS_DROP_QUERY);

        // create table new
        onCreate(db);
    }
}
