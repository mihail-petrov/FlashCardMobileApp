package com.mentormateacademy.flashcardmobileclient.database.interfaces;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mentormateacademy.flashcardmobileclient.database.helper.DatabaseHelper;

import java.util.ArrayList;

public abstract class Repository<T> {

    //
    private SQLiteDatabase database;
    private SQLiteOpenHelper databaseHelper;

    public Repository(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
        this.database = databaseHelper.getWritableDatabase();
    }

    protected SQLiteDatabase getDatabase() {
        return this.database;
    }

    /**
     * @param element
     */
    public abstract void create(T element);

    /**
     * @return
     */
    public abstract ArrayList<T> readAll();

    /**
     * @return
     */
    public abstract Cursor readAllCursor();

    /**
     * @param element
     */
    public abstract void update(T element);

    /**
     * @param element
     */
    public abstract void delete(T element);

    /**
     *
     */
    public abstract void deleteAll();


}
