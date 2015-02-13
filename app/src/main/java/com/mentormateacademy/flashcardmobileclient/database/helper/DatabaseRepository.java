package com.mentormateacademy.flashcardmobileclient.database.helper;


import android.content.Context;

import com.mentormateacademy.flashcardmobileclient.database.repositories.CardRepository;
import com.mentormateacademy.flashcardmobileclient.database.repositories.DeckRepository;

public class DatabaseRepository {

    /*
     ==============================================================*/

    private static DatabaseRepository repositoryInstance = null;

    private DeckRepository deckRepository;
    private CardRepository cardRepository;

    /*
     ==============================================================*/

    private DatabaseRepository(Context context) {
        this.deckRepository = new DeckRepository(context);
        this.cardRepository = new CardRepository(context);
    }

    /*
     ==============================================================*/

    public DatabaseRepository getRepository(Context context) {

        if (repositoryInstance == null) {
            repositoryInstance = new DatabaseRepository(context);
        }
        return repositoryInstance;
    }

    /*
     ==============================================================*/

    public DeckRepository getDeckRepository() {
        return this.deckRepository;
    }

    public CardRepository getCardRepository() {
        return this.cardRepository;
    }
}
