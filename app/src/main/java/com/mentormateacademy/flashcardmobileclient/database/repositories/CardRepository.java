package com.mentormateacademy.flashcardmobileclient.database.repositories;

import android.content.Context;
import android.database.Cursor;

import com.mentormateacademy.flashcardmobileclient.database.interfaces.IDatabaseRepository;
import com.mentormateacademy.flashcardmobileclient.models.Card;

import java.util.ArrayList;


public class CardRepository implements IDatabaseRepository<Card> {

    private Context repositoryContext;

    public CardRepository(Context context) {
        repositoryContext = context;
    }

    @Override
    public void create(Card element) {

    }

    @Override
    public ArrayList<Card> readAll() {
        return null;
    }

    @Override
    public Cursor readAllCursor() {
        return null;
    }

    @Override
    public void update(Card element) {

    }

    @Override
    public void delete(Card element) {

    }

    @Override
    public void deleteAll() {

    }
}
