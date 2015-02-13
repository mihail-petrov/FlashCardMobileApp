package com.mentormateacademy.flashcardmobileclient.database.interfaces;


import android.database.Cursor;

import java.util.ArrayList;

public interface IDatabaseRepository<T> {

    /**
     * @param element
     */
    public void create(T element);

    /**
     * @return
     */
    public ArrayList<T> readAll();

    /**
     * @return
     */
    public Cursor readAllCursor();

    /**
     * @param element
     */
    public void update(T element);

    /**
     * @param element
     */
    public void delete(T element);

    /**
     *
     */
    public void deleteAll();
}
