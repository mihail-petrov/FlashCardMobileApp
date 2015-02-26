package com.mentormateacademy.flashcardmobileclient.database.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import com.mentormateacademy.flashcardmobileclient.configurations.DatabaseConfiguration;
import com.mentormateacademy.flashcardmobileclient.database.interfaces.Repository;
import com.mentormateacademy.flashcardmobileclient.models.User;

import java.util.ArrayList;

public class UserRepository extends Repository<User> {

    private long objectId;

    public UserRepository(Context context) {
        super(context, DatabaseConfiguration.TABLE_USERS);
    }

    @Override
    public void create(User element) {
        ContentValues values = getContentValueObject(element);
        long id = getDatabase().insert(DatabaseConfiguration.TABLE_USERS, null, values);
        this.objectId = id;
    }

    public long getLastInsertedId(){
        return this.objectId;
    }

    @Override
    public ArrayList<User> readAll() {
        return null;
    }

    @Override
    public ArrayList<User> readBy(Bundle arguments) {

        // create empty ArrayList
        ArrayList<User> usersArrayList = new ArrayList<>();

        // get cursor
        Cursor deckCursorPointer = readByCursor(arguments);

        // collect data
        while (deckCursorPointer.moveToNext()) {

            User user = getUser(deckCursorPointer);
            usersArrayList.add(user);
        }

        return usersArrayList;
    }

    @Override
    public void update(User element) {

    }

    @Override
    public void delete(User element) {

    }


    // DeckRepository Utility Methods
    // =====================================
    public ContentValues getContentValueObject(User element){

        ContentValues values = new ContentValues(2);

        values.put(DatabaseConfiguration.TABLE_USERS_EMAIL, element.getEmail());
        values.put(DatabaseConfiguration.TABLE_USERS_PASS, element.getPassword());
        return values;
    }

    public User getUser(Cursor cursorPointer) {

        // get all elements
        long id         = cursorPointer
                .getLong(cursorPointer.getColumnIndex(DatabaseConfiguration.TABLE_USERS_ID));

        String email  = cursorPointer
                .getString(cursorPointer.getColumnIndex(DatabaseConfiguration.TABLE_USERS_EMAIL));

        String pass    = cursorPointer
                .getString(cursorPointer.getColumnIndex(DatabaseConfiguration.TABLE_USERS_PASS));

        // populate object
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(pass);

        return user;
    }
}
