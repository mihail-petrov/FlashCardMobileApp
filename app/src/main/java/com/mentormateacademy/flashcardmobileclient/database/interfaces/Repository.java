package com.mentormateacademy.flashcardmobileclient.database.interfaces;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.mentormateacademy.flashcardmobileclient.database.helper.DatabaseHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public abstract class Repository<T> {

    //
    private SQLiteDatabase database;
    private SQLiteOpenHelper databaseHelper;

    protected String repositoryTableName;

    public Repository(Context context, String tableName) {
        this.databaseHelper = new DatabaseHelper(context);
        this.database = databaseHelper.getWritableDatabase();

        this.repositoryTableName = tableName;
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


    public Cursor readByCursor(Bundle arguments){

        Set<String> tableColumns  = arguments.keySet();
        Iterator<String> iterator = tableColumns.iterator();

        ArrayList<String> where = new ArrayList<>();
        StringBuilder builder   = new StringBuilder();

        while(iterator.hasNext()) {
            String element = iterator.next();

            if(iterator.hasNext()) {
                builder.append(element).append("=? AND ");
            }
            else {
                builder.append(element).append("=?");
            }

            where.add(arguments.getString(element));
        }

        // database query
        String databaseQuery = builder.toString();

        // where arguments array
        String[] whereArguments = where.toArray(new String[where.size()]);

        return getDatabase().query(this.repositoryTableName, null,
                databaseQuery, whereArguments, null, null, null);
    }
}
