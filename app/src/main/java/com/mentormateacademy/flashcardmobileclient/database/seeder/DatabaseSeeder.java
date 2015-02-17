package com.mentormateacademy.flashcardmobileclient.database.seeder;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.mentormateacademy.flashcardmobileclient.database.helper.DatabaseRepository;
import com.mentormateacademy.flashcardmobileclient.helpers.RandomGenerator;
import com.mentormateacademy.flashcardmobileclient.models.Card;
import com.mentormateacademy.flashcardmobileclient.models.Deck;


public class DatabaseSeeder extends Application {

    private static final boolean SEED_DATABASE = false;
    private static final boolean DELETE_TABLES_BEFORE_SEED = false;
    private DatabaseRepository databaseRepository;
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        if (SEED_DATABASE == true) {
            this.context = getApplicationContext();
            this.databaseRepository = DatabaseRepository.getRepository(this.context);

            if (DELETE_TABLES_BEFORE_SEED == true) {
                this.deleteDatabase();
            }
            this.seedDatabase();
        }
    }

    public void seedDatabase() {

        Log.d("DB_HELPER", "Start seed database table");

        RandomGenerator randomGenerator = new RandomGenerator();

        for (int i = 0; i < 10; i++) {

            Deck deckObject = new Deck();

            String deckTitle = randomGenerator.generateString(5, 15, true);
            deckObject.setTitle(deckTitle);
            deckObject.setStrategyId(1);

            // add new object to the database
            this.databaseRepository.getDeckRepository().create(deckObject);
        }

        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < 10; j++) {

                Card cardObject = new Card();

                long deckCategory = i;

                //String cardFrontTitle = randomGenerator.generateString(5, 10, false);
                String cardFrontTitle = "Front";
                String cardFrontContent = randomGenerator.generateString(5, 20, true);

                //String cardBackTitle = randomGenerator.generateString(5, 10, false);
                String cardBackTitle = "Back";
                String cardBackContent = randomGenerator.generateString(5, 20, true);

                //String cardExtraTitle = randomGenerator.generateString(5, 10, false);
                String cardExtraTitle = "Extra";
                String cardExtraContent = randomGenerator.generateString(5, 20, true);

                // populate object
                cardObject.setDeckId(deckCategory);
                cardObject.setFrontTitle(cardFrontTitle);
                cardObject.setFrontContent(cardFrontContent);
                cardObject.setBackTitle(cardBackTitle);
                cardObject.setBackContent(cardBackContent);
                cardObject.setExtraTitle(cardExtraTitle);
                cardObject.setExtraContent(cardExtraContent);

                // add new object to the database
                databaseRepository.getCardRepository().create(cardObject);
            }
        }
    }

    public void deleteDatabase() {

        // remove all data in deck table
        this.databaseRepository.getDeckRepository().deleteAll();

        // remove all data in card table
        this.databaseRepository.getCardRepository().deleteAll();
    }
}
