package com.mentormateacademy.flashcardmobileclient.database.repositories;

import android.content.Context;
import android.database.Cursor;

import com.mentormateacademy.flashcardmobileclient.database.interfaces.IDatabaseRepository;
import com.mentormateacademy.flashcardmobileclient.models.Deck;

import java.util.ArrayList;

public class DeckRepository implements IDatabaseRepository<Deck> {

    private Context repositoryContext;

    public DeckRepository(Context context) {
        repositoryContext = context;
    }

    @Override
    public void create(Deck element) {

    }

    @Override
    public ArrayList<Deck> readAll() {
        return null;
    }

    @Override
    public Cursor readAllCursor() {
        return null;
    }

    @Override
    public void update(Deck element) {

    }

    @Override
    public void delete(Deck element) {

    }

    @Override
    public void deleteAll() {

    }
}
