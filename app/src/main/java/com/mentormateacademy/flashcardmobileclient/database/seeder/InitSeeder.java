package com.mentormateacademy.flashcardmobileclient.database.seeder;

import android.content.Context;
import android.os.Bundle;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.configurations.DatabaseConfiguration;
import com.mentormateacademy.flashcardmobileclient.database.helper.DatabaseRepository;
import com.mentormateacademy.flashcardmobileclient.models.Card;
import com.mentormateacademy.flashcardmobileclient.models.Deck;


public class InitSeeder {

    private DatabaseRepository databaseRepository;
    private Context context;


    public InitSeeder(Context context, long userId){
        this.databaseRepository = DatabaseRepository.getRepository(context);
        initSeed(context, userId);
    }

    public void initSeed(Context context, long userId) {

        // create deck
        Deck deckObject = new Deck();

        deckObject.setTitle("Sample deck");
        deckObject.setUserId(userId);
        deckObject.setStrategyId(1);
        this.databaseRepository.getDeckRepository().create(deckObject);
        long deckId = this.databaseRepository.getDeckRepository().getLastInsertedId();

        String[] frontCards = context.getResources().getStringArray(R.array.sample_card_front);
        String[] backCards = context.getResources().getStringArray(R.array.sample_card_back);
        String[] extraCards = context.getResources().getStringArray(R.array.sample_card_extra);

        // get deck element
        Bundle deckArguments = new Bundle();
        deckArguments.putString(DatabaseConfiguration.TABLE_DECKS_ID, String.valueOf(deckId));
        Deck deckElement = databaseRepository.getDeckRepository().readBy(deckArguments).get(0);


        // create 5 cards in to the deck
        for(int i = 0; i < 5; i++) {

            Card cardObject = new Card();
            cardObject.setDeckId(deckId);
            cardObject.setFrontTitle("Front");
            cardObject.setFrontContent(frontCards[i]);
            cardObject.setBackTitle("Back");
            cardObject.setBackContent(backCards[i]);
            cardObject.setExtraTitle("Extra");
            cardObject.setExtraContent(extraCards[i]);

            // change cards count
            int currentCardCount = deckElement.getCardSize();
            deckElement.setCardSize(currentCardCount + 1);

            // add new object to the database
            this.databaseRepository.getCardRepository().create(cardObject);

            // update deck element
            databaseRepository.getDeckRepository().update(deckElement, deckArguments);
        }
    }
}
