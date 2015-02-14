package com.mentormateacademy.flashcardmobileclient.database.seeder.models;

import android.content.Context;

import com.mentormateacademy.flashcardmobileclient.database.helper.DatabaseRepository;
import com.mentormateacademy.flashcardmobileclient.database.repositories.CardRepository;
import com.mentormateacademy.flashcardmobileclient.database.seeder.interfaces.ISeeder;
import com.mentormateacademy.flashcardmobileclient.models.Card;

public class CardSeeder implements ISeeder {

    private CardRepository repository;

    public CardSeeder(Context context, DatabaseRepository dbRepository) {
        this.repository = dbRepository.getRepository(context).getCardRepository();
    }

    @Override
    public void createItem() {
        Card cardObject = new Card();

    }
}
