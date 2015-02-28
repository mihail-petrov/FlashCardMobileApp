package com.mentormateacademy.flashcardmobileclient.data.loaders.cursorProviders;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import com.mentormateacademy.flashcardmobileclient.configurations.DatabaseConfiguration;
import com.mentormateacademy.flashcardmobileclient.data.loaders.interfaces.BaseCursorProvider;
import com.mentormateacademy.flashcardmobileclient.database.helper.DatabaseRepository;
import com.mentormateacademy.flashcardmobileclient.database.repositories.CardRepository;

public class CardCursorProvider extends BaseCursorProvider {

    private CardRepository cardRepository;
    private long deckId;

    public CardCursorProvider(Context context, long deckId) {
        super(context);

        this.cardRepository = DatabaseRepository.getRepository(context).getCardRepository();
        this.deckId         = deckId;
    }

    @Override
    public Cursor loadInBackground() {

        Bundle bundle = new Bundle();
        bundle.putString(DatabaseConfiguration.TABLE_CARDS_DECK_ID, String.valueOf(this.deckId));

        return this.cardRepository.readByCursor(bundle);
    }
}
